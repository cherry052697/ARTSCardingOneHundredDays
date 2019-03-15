package com.cherry.netty.demo.echo;


import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class EchoServerHandler extends ChannelHandlerAdapter {

	@SuppressWarnings("unused")
	private int counter = 0;

	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		/*String body = (String) msg;
		System.out.println("this is "+ ++counter+" times receive client:["+body+"]");
		body += "$_";
		ByteBuf echo = Unpooled.copiedBuffer(body.getBytes());
		ctx.writeAndFlush(echo);*/
		System.out.println("reveive clinet: ["+msg+"]");

	}

	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}

}
