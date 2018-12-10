package com.cherry.netty.demo.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class EchoServer {

	public void bind(int port) throws InterruptedException {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG,100)
				.handler(new LoggingHandler(LogLevel.INFO))
				.childHandler(new ChildChannelHandler());
			// 绑定端口，同步等待成功
			ChannelFuture f = b.bind(port).sync();
			// 等待服务端监听端口关闭
			f.channel().closeFuture().sync();
		} finally {
			// 优雅退出，释放线程池资源
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

		@Override
		protected void initChannel(SocketChannel arg0) throws Exception {
			// DelimiterBasedFrameDecoder以分隔符作为码流结束标识的消息解码
//			ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
//			arg0.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
			
			// FixedLengthFrameDecoder用于对固定长度的消息进行解码
			arg0.pipeline().addLast(new FixedLengthFrameDecoder(20));
			arg0.pipeline().addLast(new StringDecoder());
			arg0.pipeline().addLast(new EchoServerHandler());
			
		}
		
	}
	public static void main(String[] args) throws InterruptedException {
		int port = 8080;
		if(args != null && args.length > 0){
			port = Integer.valueOf(args[0]);
		}
		new EchoServer().bind(port);
	}

}
