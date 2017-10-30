package com.td.socketserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.td.initchannel.WebsocketChatServerInitializer;
import com.td.util.LogUtil;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

@Service
public class WebsocketChatServer {

	@Autowired
	WebsocketChatServerInitializer websocketChatServerInitializer;

	@Value("${wss.server.port}")
	private Integer port;

	private EventLoopGroup bossGroup;
	private EventLoopGroup workerGroup;

	public WebsocketChatServer(int port) {
		this.port = port;
	}

	public WebsocketChatServer() {
	}

	public void run() throws Exception {

		bossGroup = new NioEventLoopGroup(); // (1)
		workerGroup = new NioEventLoopGroup(8);
		try {
			ServerBootstrap b = new ServerBootstrap(); // (2)
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class) // (3)
					.childHandler(websocketChatServerInitializer) // (4)
					.option(ChannelOption.SO_BACKLOG, 128) // (5)
					.childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

			LogUtil.error("WebsocketChatServer启动了,port:" + port);

			// 绑定端口，开始接收进来的连接
			ChannelFuture f = b.bind(port).sync(); // (7)

			// heard();
			// 等待服务器 socket 关闭 ，可以优雅地关闭你的服务器。
			f.channel().closeFuture().sync();

		} finally {
			stop();
		}
	}

	/**
	 * @Title: stop
	 * @Description: 关闭
	 * @author 米雪铭
	 */
	public void stop() {
		workerGroup.shutdownGracefully();
		bossGroup.shutdownGracefully();
		LogUtil.error("WebsocketChatServer 关闭了");
	}

}
