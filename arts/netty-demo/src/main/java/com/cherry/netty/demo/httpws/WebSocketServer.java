package com.cherry.netty.demo.httpws;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSocketServer {
	public void run(int port) throws InterruptedException{
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline pipeline = ch.pipeline();
					pipeline.addLast("http-codec",new HttpServerCodec());
					pipeline.addLast("aggregator",new HttpObjectAggregator(65536));
					ch.pipeline().addLast("http-chunked",new ChunkedWriteHandler());
					pipeline.addLast("handler",new WebSocketServerHandler());
					
				}
			});
			Channel ch = b.bind(port).sync().channel();
			System.out.println("web socket server started at port:"+port+'.');
			System.out.println("open your browser and navigate to http://localhost:"+port+'/');
			ch.closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		int port = 8080;
		if (args.length > 1) {
			port = Integer.valueOf(args[0]);
		}
		new WebSocketServer().run(port);
	}

}
