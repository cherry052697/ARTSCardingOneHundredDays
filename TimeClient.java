package com.cherry.netty.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class TimeClient {
	public void connect(int port,String host) throws InterruptedException{
		EventLoopGroup group = new  NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
			.option(ChannelOption.TCP_NODELAY, true)
			.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel sc) throws Exception {
//					sc.pipeline().addLast(new TimeClientHandler());
					// tcp粘包问题解决
					sc.pipeline().addLast(new LineBasedFrameDecoder(1024));
					sc.pipeline().addLast(new StringDecoder());
					sc.pipeline().addLast(new TimeClientHandler());
				}
			});
			ChannelFuture f = b.connect(host,port).sync();
			f.channel().closeFuture();
		} finally {
			group.shutdownGracefully();
		}
	}
	public static void main(String[] args) throws InterruptedException {
		int port = 8080;
		if(args != null && args.length > 0){
			port = Integer.valueOf(args[0]);
		}
		new TimeClient().connect(port, "127.0.0.1");
	}

}
