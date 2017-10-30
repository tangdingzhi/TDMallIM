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
import com.td.util.StringUtil;
import com.td.util.ThreadPoolUtil;
import com.td.util.UUIDUtil;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @ClassName: MsgSend
 * @Description: 消息发送中心
 * @author 米雪铭
 * @date: 2017年10月18日 上午10:12:23
 */
@Component
public class MsgSend {

	@Autowired
	private MQOffer mQOffer;
	@Autowired
	private ILogService logServer;
	@Autowired
	private IServerService serverService;

	/**
	 * @Title: text
	 * @Description: 用于将socketJson转换为正确的返回类型，减少代码书写
	 * @author 米雪铭
	 * @param socketJson
	 * @return
	 */
	public static TextWebSocketFrame text(SocketJson socketJson) {
		return new TextWebSocketFrame(JsonUtil.toJsonStr(socketJson));
	}

	/**
	 * @Title: send
	 * @Description: 直接发送消息
	 * @author 米雪铭
	 * @param msg
	 */
	public ChannelFuture send(String msg) {
		SocketJson socketJson = (SocketJson) JsonUtil.toBean(msg, SocketJson.class);
		if (StringUtil.isNotNull(socketJson)) {
			String toID = socketJson.getToID();
			Channel toChannel = null;
			// 没有发送id时的获取之前保存的连接ID以发送
			if (StringUtil.isNull(toID)) {
				OnlineClient client = ClientUtil.getClient(socketJson.getFromID());
				if (null != client)
					toChannel = ClientUtil.getClientChannel(client.getToID());
			} else {
				toChannel = ClientUtil.getClientChannel(toID);
			}
			if (toChannel != null)
				return toChannel.writeAndFlush(new TextWebSocketFrame(msg));
		}
		return null;
	}

	/**
	 * @Title: SendMsgSave
	 * @Description: 发送消息并保存
	 * @author 米雪铭
	 * @param msg
	 */
	public ChannelFuture sendSave(String msg) {
		ChannelFuture channelFuture = send(msg);
		if (null != channelFuture) {
			channelFuture.addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					ThreadPoolUtil.execute(new Runnable() {
						public void run() {
							SocketJson bean = (SocketJson) JsonUtil.toBean(msg, SocketJson.class);
							// 回调成功时保存消息记录
							if (future.isSuccess()) {
								bean.setId(UUIDUtil.getUUID());
								// 这里的channel其实是toID方，也就是接收方的channel
								Channel toChannel = future.channel();
								OnlineClient toClient = ClientUtil.getClient(toChannel);
								// 获取缺省字段
								saveHelp(bean, toClient);
								// 获取商铺信息
								bean.setShopID(toClient.getShopID());
								bean.setShopName(toClient.getShopName());
								logServer.save(bean);
							} else {
								// 失败则返回错误信息
								Channel fromChannel = ClientUtil.getClientChannel(bean.getFromID());
								if (fromChannel != null) {
									SocketJson json = new SocketJson();
									json.setToID(bean.getFromID());
									json.setFromName("系统提示");
									json.setContent("消息发送失败！");
									json.setSendTime(new Date().getTime());
									json.setStatus(500);
									json.setType(1);
									// 存入消息队列中
									mQOffer.offer(JsonUtil.toJsonStr(json));
								}
							}
						}
					});
				}

				/**
				 * @Title: saveHelp
				 * @Description: 为了以后用户端不需要传递toID和fromID考虑
				 * @author 米雪铭
				 * @param bean
				 * @param toClient
				 */
				private void saveHelp(SocketJson bean, OnlineClient toClient) {
					// 获取发送方和接收方的信息
					String toID = bean.getToID();
					String fromID = bean.getFromID();
					String shopID = null;
					String shopName = null;
					if (StringUtil.isNotNull(toClient)) {
						// 此处的toID应该为接收方自己的id
						if (StringUtil.isNull(toID)) {
							toID = toClient.getId();
							bean.setToID(toID);
						}
						// 此处的fromID应该为接收方的toID
						// 对于客服，因为是一对多，所以并没有保存ToID，此处必然会获取到空值
						if (StringUtil.isNull(fromID)) {
							fromID = toClient.getToID();
							bean.setFromID(fromID);
						}
						shopID = toClient.getShopID();
						// 若没有放入商铺id，则查询获取id和名称
						if (StringUtil.isNull(shopID)) {
							Server server = serverService.get(toID);
							if (StringUtil.isNull(server))
								server = serverService.get(fromID);
							if (StringUtil.isNotNull(server)) {
								shopID = server.getShopID();
								shopName = server.getShopName();
								toClient.setShopID(shopID);
								toClient.setShopName(shopName);
							}
						}
					} else {
						toClient = new OnlineClient();
					}
					OnlineClient to = ClientUtil.getClient(toID);
					OnlineClient from = ClientUtil.getClient(fromID);
					if (StringUtil.isNotNull(to))
						bean.setToName(to.getName());
					if (StringUtil.isNotNull(from))
						bean.setFromName(from.getName());
				}
			});
		}
		return channelFuture;
	}
}
