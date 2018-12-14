package com.cherry.netty.demo.protocolstack;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.cherry.netty.demo.protocolstack.decode.NettyMessageDecoder;
import com.cherry.netty.demo.protocolstack.encode.NettyMessageEncoder;
import com.cherry.netty.demo.protocolstack.hearthandler.HeartBeatReqHandler;
import com.cherry.netty.demo.protocolstack.loginhandler.LoginAuthReqHandler;
import com.cherry.netty.demo.protocolstack.pojo.NettyConstant;

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
					ch.pipeline().addLast("meaageEncoder",new NettyMessageEncoder());
					ch.pipeline().addLast("readTimeoutHandler",new ReadTimeoutHandler(50));
					ch.pipeline().addLast("loginAuthHandler",new LoginAuthReqHandler());
					ch.pipeline().addLast("heartBeatHandler",new HeartBeatReqHandler());
					
				}

			});
			// 发起异步连接操作
			ChannelFuture future = b.connect(
					new InetSocketAddress(host, port), 
					new InetSocketAddress(NettyConstant.LOCALIP, NettyConstant.LOCAL_PORT)).sync();
			System.out.println("Netty client start ok . remoteAddress( "+host+":"+port+"),localAddress("+NettyConstant.LOCALIP+":"+NettyConstant.LOCAL_PORT+")");
			
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
