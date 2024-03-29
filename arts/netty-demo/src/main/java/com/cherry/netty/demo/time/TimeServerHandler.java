package com.cherry.netty.demo.time;

import java.sql.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeServerHandler extends ChannelHandlerAdapter {

	private int counter;

	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// super.channelRead(ctx, msg);
		ByteBuf buf = (ByteBuf) msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		/*
		 * String body = new String(req, "UTF-8");
		 * System.out.println("the time server reveive order : "+body); String
		 * currentTime = "query time order".equalsIgnoreCase(body)?new
		 * Date(System.currentTimeMillis()).toString():"bad order"; ByteBuf resp
		 * = Unpooled.copiedBuffer(currentTime.getBytes()); ctx.write(resp);
		 */
		/*
		 * String body = new String(req, "UTF-8").substring(0,
		 * req.length-System.getProperty("line.separator").length());
		 * System.out.println("the time server reveive order : "
		 * +body+"; the counter is : "+ ++counter); String currentTime =
		 * "query time order".equalsIgnoreCase(body)?new
		 * Date(System.currentTimeMillis()).toString():"bad order"; currentTime
		 * = currentTime+System.getProperty("line.separator"); ByteBuf resp =
		 * Unpooled.copiedBuffer(currentTime.getBytes());
		 * ctx.writeAndFlush(resp);
		 */
		// tcp粘包问题解决更改
		String body = (String) msg;
		System.out.println("the time server reveive order : " + body + "; the counter is : " + ++counter);
		String currentTime = "query time order".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString()
				: "bad order";
		currentTime = currentTime + System.getProperty("line.separator");
		ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
		ctx.writeAndFlush(resp);

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
