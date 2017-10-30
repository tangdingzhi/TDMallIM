package com.td.initchannel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.td.handler.HttpRequestHandler;
import com.td.handler.TextWebSocketFrameHandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

@Service
public class WebsocketChatServerInitializer extends ChannelInitializer<SocketChannel> { // 1

	@Autowired
	TextWebSocketFrameHandler handler;

	@Override
	public void initChannel(SocketChannel ch) throws Exception {// 2
		ChannelPipeline pipeline = ch.pipeline();

		pipeline.addLast(new HttpServerCodec());
		pipeline.addLast(new HttpObjectAggregator(64 * 1024));
		pipeline.addLast(new ChunkedWriteHandler());
		pipeline.addLast(new HttpRequestHandler("/im"));
		pipeline.addLast(new WebSocketServerProtocolHandler("/im"));
		pipeline.addLast(handler);

	}
}
