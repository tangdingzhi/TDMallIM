package com.td.msg;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.td.bean.OnlineClient;
import com.td.bean.SocketJson;
import com.td.domain.Server;
import com.td.queue.MQOffer;
import com.td.service.ILogService;
import com.td.service.IServerService;
import com.td.util.ClientUtil;
import com.td.util.JsonUtil;
import com.td.util.LogUtil;
import com.td.util.ThreadPoolUtil;

import io.netty.channel.Channel;

/**
 * @ClassName: MsgProcess
 * @Description: 消息处理中心
 * @author 米雪铭
 * @date: 2017年9月11日 下午2:53:11
 */
@Component
public class MsgProcess {

	@Autowired
	private MQOffer mQOffer;
	@Autowired
	private ILogService logServer;
	@Autowired
	private IServerService serverService;
	@Autowired
	private ClientUtil clientUtil;

	/**
	 * @Title: getMsg
	 * @Description: 获取消息的处理
	 * @author 米雪铭
	 * @param sid
	 * @param msg
	 * @return
	 */
	public void getMsg(Channel channel, String msg) {
		LogUtil.info(msg);
		SocketJson bean;
		try {
			bean = (SocketJson) JsonUtil.toBean(msg, SocketJson.class);
		} catch (Exception e) {
			channel.writeAndFlush(MsgSend.text(new SocketJson("", "数据格式错误！", 8)));
			return;
		}
		switch (bean.getType()) {
		// 通讯
		case 0:
			mQOffer.offer(msg);
			break;
		// 初次连接分配客服
		case 1:
			// 设置用户在线
			clientOnline(channel, bean);
			// 开启线程进行分配客服处理以防止出现阻塞
			ThreadPoolUtil.execute(new Runnable() {
				public void run() {
					long time = System.nanoTime();
					// 分配客服
					Server server = serverService.getServer(bean);
					LogUtil.debug("给客户" + bean.getFromName() + "分配客服：" + server);
					// 构建返回参数
					SocketJson json = new SocketJson();
					if (null == server) {
						json.setStatus(500);
						json.setContent("该商铺没有在线的客服！");
					} else {
						String serverID = server.getId();
						// 将客服设置到在线用户的toID中
						ClientUtil.getClient(channel).setToID(serverID);
						// 设置返回信息
						json.setFromID(serverID);
						json.setFromName(server.getName());
						json.setStatus(100);
						String log = JsonUtil.toJsonStr(logServer.clientLog(serverID, bean.getFromID()));
						json.setContent(log);
						// 将聊天记录发一份给客服
						SocketJson serverJson = new SocketJson();
						serverJson.setContent(log);
						serverJson.setFromID(bean.getFromID());
						serverJson.setFromName(bean.getFromName());
						serverJson.setStatus(100);
						serverJson.setToID(serverID);
						serverJson.setType(1);
						mQOffer.offer(JsonUtil.toJsonStr(serverJson));
					}
					json.setToID(bean.getFromID());
					json.setType(1);
					json.setSendTime(new Date().getTime());
					mQOffer.offer(JsonUtil.toJsonStr(json));
					LogUtil.debug("分配客服耗时：" + (System.nanoTime() - time) / 1000000);
				}
			});
			break;
		// 初次连接不分配客服
		case 2:
			// 设置用户在线
			clientOnline(channel, bean);
			break;
		// 错误参数
		default:

			break;
		}
	}

	/**
	 * @Title: clientOnline
	 * @Description: 用户上线
	 * @author 米雪铭
	 * @param channel
	 * @param bean
	 * @return
	 */
	public OnlineClient clientOnline(Channel channel, SocketJson bean) {
		String clientID = bean.getFromID();
		Channel clientChannel = ClientUtil.getClientChannel(clientID);
		// 将之前挤下线
		if (null != clientChannel) {
			clientChannel.writeAndFlush(MsgSend.text(new SocketJson(clientID, "用户重复登陆！", 9)));
			clientChannel.close();
			ClientUtil.reClient(clientID);
		}
		OnlineClient client = new OnlineClient();
		client.setChannel(channel);
		client.setId(clientID);
		client.setName(bean.getFromName());
		clientUtil.setClient(clientID, client);
		channel.writeAndFlush(MsgSend.text(new SocketJson(clientID, "已登陆！", 6, 100)));
		return client;
	}

}
