package com.td.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.td.bean.OnlineClient;
import com.td.bean.SocketJson;
import com.td.msg.MsgSend;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @ClassName: OnlineClient
 * @Description: 在线客户类
 * @author 米雪铭
 * @date: 2017年8月24日 上午9:51:07
 */
@Component
public class ClientUtil {

	/**
	 * 连接列表
	 */
	public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	/**
	 * 用户id-在线用户列表
	 */
	private final static Map<String, OnlineClient> IDTOCLIENTMAP = new ConcurrentHashMap<>();
	/**
	 * 连接-用户id列表
	 */
	private final static Map<Channel, String> CLIENTTOIDMAP = new ConcurrentHashMap<>();

	/**
	 * @Title: setClient
	 * @Description: 新增在线用户
	 * @author 米雪铭
	 * @param name
	 * @param client
	 */
	public void setClient(String name, OnlineClient client) {
		if (StringUtil.isNotNull(name) && StringUtil.isNotNull(client)) {
			IDTOCLIENTMAP.put(name, client);
			CLIENTTOIDMAP.put(client.getChannel(), name);
		}
	}

	/**
	 * @Title: getClient
	 * @Description: 根据id获取用户
	 * @author 米雪铭
	 * @param id
	 * @return
	 */
	public static OnlineClient getClient(String id) {
		if (StringUtil.isNull(id))
			return null;
		return IDTOCLIENTMAP.get(id);
	}

	/**
	 * @Title: getClient
	 * @Description: 根据连接获得用户
	 * @author 米雪铭
	 * @param channel
	 * @return
	 */
	public static OnlineClient getClient(Channel channel) {
		if (StringUtil.isNull(channel))
			return null;
		return getClient(CLIENTTOIDMAP.get(channel));
	}

	/**
	 * @Title: getClientChannel
	 * @Description: 根据id获取用户连接
	 * @author 米雪铭
	 * @param id
	 * @return
	 */
	public static Channel getClientChannel(String id) {
		OnlineClient client = getClient(id);
		if (StringUtil.isNull(client))
			return null;
		return client.getChannel();
	}

	/**
	 * @Title: reClient
	 * @Description: 移除指定用户
	 * @author 米雪铭
	 * @param id
	 * @return
	 */
	public static boolean reClient(String id) {
		// id不能为空
		if (StringUtil.isNotNull(id)) {
			OnlineClient remove = IDTOCLIENTMAP.remove(id);
			// 成功移除id-用户
			if (StringUtil.isNotNull(remove)) {
				String removeId = CLIENTTOIDMAP.remove(remove.getChannel());
				// 成功移除连接-id且id和传入id相同
				if (StringUtil.eq(id, removeId)) {
					// 构建返回信息
					OnlineClient client = getClient(remove.getToID());
					if (StringUtil.isNotNull(client)) {
						// 客户只需要通知对应的客服
						client.getChannel().writeAndFlush(MsgSend.text(new SocketJson(id, "对方已离线！", 10)));
					} else {
						// 客服需要遍历在线用户以通知对方
						for (String key : IDTOCLIENTMAP.keySet()) {
							client = getClient(key);
							if (StringUtil.isNotNull(client) && StringUtil.eq(id, client.getToID())) {
								client.getChannel().writeAndFlush(MsgSend.text(new SocketJson(id, "对方已离线！", 10)));
								// 防止前端没有处理导致错误提示
								client.setToID(null);
							}
						}
					}
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @Title: reClient
	 * @Description: 移除指定用户
	 * @author 米雪铭
	 * @param channel
	 * @return
	 */
	public static boolean reClient(Channel channel) {
		if (StringUtil.isNull(channel))
			return false;
		return reClient(CLIENTTOIDMAP.get(channel));
	}

}
