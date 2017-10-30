package com.td.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.td.bean.OnlineClient;
import com.td.bean.SocketJson;
import com.td.msg.MsgSend;
import com.td.util.ClientUtil;
import com.td.util.JsonUtil;
import com.td.util.StringUtil;

/**
 * @ClassName: MQPoll
 * @Description: 从ActiveMQ队列取出消息
 * @author 米雪铭
 * @date: 2017年10月13日 下午5:23:13
 */
@Component
public class MQPoll {

	@Autowired
	MsgSend msgSend;

	@JmsListener(destination = StringUtil.MQ_NAME)
	public void receivedQueue(String msg) {
		SocketJson bean = (SocketJson) JsonUtil.toBean(msg, SocketJson.class);
		// 若没有fromName，则根据id取出来
		if (StringUtil.isNull(bean.getFromName()) && StringUtil.isNotNull(bean.getFromID())) {
			OnlineClient client = ClientUtil.getClient(bean.getFromID());
			if (StringUtil.isNotNull(client))
				bean.setFromName(client.getName());
		}
		if (bean.getType() == 0)
			msgSend.sendSave(JsonUtil.toJsonStr(bean));
		else
			msgSend.send(JsonUtil.toJsonStr(bean));
	}

}
