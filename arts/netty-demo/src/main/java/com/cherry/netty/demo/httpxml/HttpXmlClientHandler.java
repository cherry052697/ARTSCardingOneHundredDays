package com.cherry.netty.demo.httpxml;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HttpXmlClientHandler extends SimpleChannelInboundHandler<HttpXmlResponse> {

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		HttpXmlRequest request = new HttpXmlRequest(null, OrderFactory.create(123));
		ctx.writeAndFlush(request);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HttpXmlResponse msg) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("the client receive response of http header is : "+msg.getHttpResponse().headers().names());
		System.out.println("the client receive response of http body is : "+msg.getResult());
	}
	

}
