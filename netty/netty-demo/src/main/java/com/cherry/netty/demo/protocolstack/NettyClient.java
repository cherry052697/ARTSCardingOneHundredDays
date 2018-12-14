package com.cherry.netty.demo.protocolstack;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

public class NettyClient {
	
	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
	
	
	public void connect(int port,String host) throws InterruptedException{
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
			.option(ChannelOption.TCP_NODELAY, true)
			.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new NettyMessageDecoder(1024*1024, 4, 4));
					ch.pipeline().addLast("MeaageEncoder",new NettyMessageEncoder());
					ch.pipeline().addLast("ReadTimeoutHandler",new ReadTimeoutHandler(50));
					ch.pipeline().addLast("LoginAuthHandler",new LoginAuthReqHandler());
					ch.pipeline().addLast("HeartBeatHandler",new HeartBeatReqHandler());
					
				}

			});
			// 发起异步连接操作
			ChannelFuture future = b.connect(
					new InetSocketAddress(host, port), 
					new InetSocketAddress(NettyConstant.LOCALIP, NettyConstant.LOCAL_PORT)).sync();
			System.out.println("Netty client start ok . remoteAddress( "+host+":"+port+"),localAddress("+NettyConstant.LOCALIP+":"+NettyConstant.LOCAL_PORT+")");
			/*ChannelFuture future = b.connect(
					new InetSocketAddress(NettyConstant.LOCALIP, NettyConstant.LOCAL_PORT)
					,new InetSocketAddress(host, port)).sync();*/
			// 对应的channel关闭的时候，就会返回对应的channel
//			System.out.println("Netty client start ok . localAddress( "+host+":"+port+"),remoteAddress("+NettyConstant.LOCALIP+":"+NettyConstant.LOCAL_PORT+")");
			
			future.channel().closeFuture().sync();
			
		} finally {
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						TimeUnit.SECONDS.sleep(5);
						try {
							// 发起重连操作
							connect(NettyConstant.REMOTE_PORT, NettyConstant.REMOTEIP);
						} catch (Exception e) {
							System.out.println("NettyClient 发起重连操作异常");
							e.printStackTrace();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	public static void main(String[] args) throws InterruptedException {
		new NettyClient().connect(NettyConstant.REMOTE_PORT, NettyConstant.REMOTEIP);
	}
}
