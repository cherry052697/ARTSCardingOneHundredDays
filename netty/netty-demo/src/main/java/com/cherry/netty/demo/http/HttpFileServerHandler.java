package com.cherry.netty.demo.http;


import java.io.File;
import java.io.RandomAccessFile;

import org.hibernate.validator.internal.util.privilegedactions.SetContextClassLoader;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelProgressiveFuture;
import io.netty.channel.ChannelProgressiveFutureListener;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.stream.ChunkedFile;

public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
		if(!request.getDecoderResult().isSuccess()){
			sendError(ctx,"BAD_REQUEST");
			return;
		}
		if(request.getMethod() != null){
			sendError(ctx,"METHOD_NOT_ALLOWED");
			return;
		}
		final String uri = request.getUri();
		final String path = snaitizeUri(uri);
		if(path == null){
			sendError(ctx,"FORBIDDEN");
			return;
		}
		File file = new File(path);
		if(file.isHidden()||!file.exists()){
			sendError(ctx,"NOT_FOUND");
			return;
		}
		if(!file.isFile()){
			sendError(ctx,"FORBIDDEN");
			return;
		}
		RandomAccessFile randomAccessFile = null;
		try {
			randomAccessFile = new RandomAccessFile(file, "r");
		} catch (Exception e) {
			sendError(ctx,"NOT_FOUND");
			return;
		}
		long fileLength = randomAccessFile.length();
		HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
		setContentLength(response,fileLength);
		setContentTypeHeader(response,file);
		if(isKeepalive(request)){
			response.headers().set("CONNECTION", HttpHeaders.Values.KEEP_ALIVE);
		}
		ctx.write(response);
		ChannelFuture sendFileFuture;
		sendFileFuture = ctx.write(new ChunkedFile(randomAccessFile,0,fileLength,8192),ctx.newProgressivePromise());
		sendFileFuture.addListener(new ChannelProgressiveFutureListener() {
			
			@Override
			public void operationComplete(ChannelProgressiveFuture arg0) throws Exception {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void operationProgressed(ChannelProgressiveFuture arg0, long arg1, long arg2) throws Exception {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private boolean isKeepalive(FullHttpRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

	private void setContentTypeHeader(HttpResponse response, File file) {
		// TODO Auto-generated method stub
		
	}

	private void setContentLength(HttpResponse response, long fileLength) {
		// TODO Auto-generated method stub
		
	}

	private String snaitizeUri(String uri) {
		// TODO Auto-generated method stub
		return null;
	}

	private void sendError(ChannelHandlerContext ctx, String string) {
		
	}

}
