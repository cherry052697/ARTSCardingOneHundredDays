package com.cherry.netty.demo.http;


import java.io.File;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Pattern;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelProgressiveFuture;
import io.netty.channel.ChannelProgressiveFutureListener;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.stream.ChunkedFile;

public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

	private static final Pattern INSECURE_URI = Pattern.compile("[A-Za-z0-9][-_A-Za-z0-9\\.]*");
	public HttpFileServerHandler(String url) {
	}


	private boolean isKeepalive(FullHttpRequest request) {
		return false;
	}

	private void setContentTypeHeader(HttpResponse response, File file) {
		// TODO Auto-generated method stub
		
	}

	private void setContentLength(HttpResponse response, long fileLength) {
		// TODO Auto-generated method stub
		
	}

	private String snaitizeUri(String uri) {
		try {
			uri = URLDecoder.decode(uri,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			try {
				uri = URLDecoder.decode(uri, "ISO-8859-1");
			} catch (UnsupportedEncodingException e1) {
				throw new Error();
			}
		}
		uri = uri.replace('/', File.separatorChar);
		if(uri.contains(File.separator+'.')||uri.contains('.'+File.separator)
				||uri.startsWith(".")||uri.endsWith(".")||INSECURE_URI.matcher(uri).matches()){
			return null;
		}
		return System.getProperty("user.dir")+File.separator+uri;
	}

	private void sendError(ChannelHandlerContext ctx, String string) {
		
	}
	private void sendListing(ChannelHandlerContext ctx, File dir) {
//		FullHttpRequest response = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET,uri);
		
	}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {

		if(!request.decoderResult().isSuccess()){
			sendError(ctx,HttpResponseStatus.BAD_REQUEST.toString());
			return;
		}
		if(request.method() != HttpMethod.GET){
			sendError(ctx,HttpResponseStatus.METHOD_NOT_ALLOWED.toString());
			return;
		}
		final String uri = request.uri();
		final String path = snaitizeUri(uri);
		if(path == null){
			sendError(ctx,HttpResponseStatus.FORBIDDEN.toString());
			return;
		}
		File file = new File(path);
		if(file.isHidden()||!file.exists()){
			sendError(ctx,HttpResponseStatus.NOT_FOUND.toString());
			return;
		}
		if(!file.isFile()){
			sendError(ctx,HttpResponseStatus.FORBIDDEN.toString());
			return;
		}
		RandomAccessFile randomAccessFile = null;
		try {
			randomAccessFile = new RandomAccessFile(file, "r");
		} catch (Exception e) {
			sendError(ctx,HttpResponseStatus.NOT_FOUND.toString());
			return;
		}
		long fileLength = randomAccessFile.length();
		HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
		setContentLength(response,fileLength);
		setContentTypeHeader(response,file);
		if(isKeepalive(request)){
			response.headers().set("CONNECTION",HttpHeaderValues.KEEP_ALIVE);
		}
		ctx.write(response);
		ChannelFuture sendFileFuture;
		sendFileFuture = ctx.write(new ChunkedFile(randomAccessFile,0,fileLength,8192),ctx.newProgressivePromise());
		sendFileFuture.addListener(new ChannelProgressiveFutureListener() {

			@Override
			public void operationProgressed(ChannelProgressiveFuture future, long progress, long total)
					throws Exception {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void operationComplete(ChannelProgressiveFuture future) throws Exception {
				// TODO Auto-generated method stub
				
			}
			
		});
	
		
	}

}
