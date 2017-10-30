package com.td.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.td.msg.MsgProcess;
import com.td.util.ClientUtil;
import com.td.util.LogUtil;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

@Sharable
@Service
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

	@Autowired
	MsgProcess process;

	/**
	 * 收到消息
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception { // (1)
		process.getMsg(ctx.channel(), msg.text());
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception { // (2)
		Channel incoming = ctx.channel();
		ClientUtil.channels.add(incoming);
		LogUtil.debug("Client:" + incoming.remoteAddress() + "加入");
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception { // (3)
		Channel incoming = ctx.channel();
		ClientUtil.channels.remove(incoming);
		ClientUtil.reClient(incoming);
		LogUtil.debug("Client:" + incoming.remoteAddress() + "离开");
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
		Channel channel = ctx.channel();
		LogUtil.debug("Client:" + channel.remoteAddress() + "在线");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
		LogUtil.debug("Client:" + ctx.channel().remoteAddress() + "掉线");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		Channel incoming = ctx.channel();
		LogUtil.debug("Client:" + incoming.remoteAddress() + "异常");
		// 当出现异常就关闭连接
		cause.printStackTrace();
		ctx.close();
	}

}