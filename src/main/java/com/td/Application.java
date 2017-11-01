package com.td;

import javax.jms.Queue;
import javax.servlet.Filter;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.td.filter.CrossDomainFilter;
import com.td.socketserver.WebsocketChatServer;
import com.td.util.StringUtil;

/**
 * @ClassName: Application
 * @Description: 启动类
 * @author 米雪铭
 * @date: 2017年10月26日 下午2:47:01
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer implements CommandLineRunner, DisposableBean {

	@Autowired
	WebsocketChatServer websocketChatServer;
	@Autowired
	CrossDomainFilter crossDomainFilter;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * @Title: queue
	 * @Description: activeMQ队列
	 * @author 米雪铭
	 * @return
	 */
	@Bean
	public Queue queue() {
		return new ActiveMQQueue(StringUtil.MQ_NAME);
	}

	/**
	 * 配置过滤器
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<Filter> someFilterRegistration() {
		FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
		registration.setFilter(crossDomainFilter);
		registration.addUrlPatterns("/*");
		registration.addInitParameter("paramName", "paramValue");
		registration.setName("sessionFilter");
		return registration;
	}

	@Override
	public void run(String... args) throws Exception {
		// 开启webSocket服务
		websocketChatServer.run();
	}

	@Override
	public void destroy() throws Exception {
		websocketChatServer.stop();
	}

}