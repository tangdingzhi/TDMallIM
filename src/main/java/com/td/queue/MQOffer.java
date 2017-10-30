package com.td.queue;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * @ClassName: MQOffer 
 * @Description: 存入ActiveMQ队列
 * @author 米雪铭
 * @date: 2017年10月13日 下午5:10:03
 */
@Component
@EnableScheduling
public class MQOffer {
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	@Autowired
	private Queue queue;

	public void offer(Object msg) {
		jmsMessagingTemplate.convertAndSend(queue, msg);
	}

}
