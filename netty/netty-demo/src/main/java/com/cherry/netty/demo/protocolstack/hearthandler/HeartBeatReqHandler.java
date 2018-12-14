package com.cherry.netty.demo.protocolstack.hearthandler;

import java.util.concurrent.TimeUnit;

import com.cherry.netty.demo.protocolstack.pojo.Header;
import com.cherry.netty.demo.protocolstack.pojo.MessageType;
import com.cherry.netty.demo.protocolstack.pojo.NettyMessage;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.ScheduledFuture;


public class HeartBeatReqHandler extends ChannelHandlerAdapter {
	
	private volatile ScheduledFuture<?> heartBeat;

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelActive(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
		NettyMessage message = (NettyMessage) msg;
		if(message.getHeader() != null && message.getHeader().getType()==MessageType.LOGIN_RESP.value()){
			heartBeat = ctx.executor().scheduleAtFixedRate(new HeartBeatReqHandler.HeartBeatTask(ctx), 0, 5000, TimeUnit.MILLISECONDS);
		}else if(message.getHeader() !=null && message.getHeader().getType()==MessageType.HEARTBEAT_RESP.value()){
			System.out.println("client receive server heart beat message:---> "+message);
		}else{
			ctx.fireChannelRead(msg);
		}
	}

	private class HeartBeatTask implements Runnable{
		
		private final ChannelHandlerContext ctx;
		
		public HeartBeatTask(final ChannelHandlerContext ctx) {
			this.ctx = ctx;
		}

		@Override
		public void run() {
			NettyMessage heatBeat = buildHeatBeat();
			System.out.println("client send heart beat message to server:---> "+heatBeat);
			ctx.writeAndFlush(heatBeat);
		}

		private NettyMessage buildHeatBeat() {
			NettyMessage message = new NettyMessage();
			Header header = new Header();
			header.setType(MessageType.HEARTBEAT_REQ.value());
			message.setHeader(header);
			return message;
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		if(heartBeat != null){
			heartBeat.cancel(true);
			heartBeat = null;
		}
		ctx.fireExceptionCaught(cause);
	}
	
	
}
