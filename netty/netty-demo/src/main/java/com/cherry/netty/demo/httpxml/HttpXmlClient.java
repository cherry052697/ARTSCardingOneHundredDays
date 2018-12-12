package com.cherry.netty.demo.httpxml;


import java.net.InetSocketAddress;

import com.cherry.netty.demo.domain.Order;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseDecoder;

public class HttpXmlClient {
	public void connect(int port) throws InterruptedException {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
			.option(ChannelOption.TCP_NODELAY, true)
			.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast("http-decoder",new HttpResponseDecoder());
					ch.pipeline().addLast("http-aggregator",new HttpObjectAggregator(65536));
					ch.pipeline().addLast("xml-decoder",new HttpXmlResponseDecoder(Order.class, true));
					ch.pipeline().addLast("http-encoder",new HttpRequestDecoder());
					ch.pipeline().addLast("xml-encoder",new HttpXmlRequestEncoder());
					ch.pipeline().addLast("xmlClintHandler",new HttpXmlClientHandler());
				}
			});
			ChannelFuture future = b.bind(new InetSocketAddress(port)).sync();
			
//			ChannelFuture future = b.connect("127.0.0.1",port).sync();
			System.out.println("http 订购服务器(client)启动，网址是： "+"http://127.0.0.1:"+port);
			future.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		int port = 8080;
		if(args != null && args.length > 0){
			port = Integer.valueOf(args[0]);
		}
		new HttpXmlClient().connect(port);
	}

}
