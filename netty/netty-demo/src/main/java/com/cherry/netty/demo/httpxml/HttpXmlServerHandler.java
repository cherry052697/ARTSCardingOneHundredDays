package com.cherry.netty.demo.httpxml;

import java.util.ArrayList;
import java.util.List;

import com.cherry.netty.demo.domain.Address;
import com.cherry.netty.demo.domain.Order;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderUtil;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class HttpXmlServerHandler extends SimpleChannelInboundHandler<HttpXmlRequest> {

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, HttpXmlRequest xmlRequest) throws Exception {
		HttpRequest request = xmlRequest.getRequest();
		Order order = (Order) xmlRequest.getBody();
		System.out.println("http server receive request : "+order);
		dobusiness(order);
		ChannelFuture future = ctx.writeAndFlush(new HttpXmlResponse(null, order));
		if(!HttpHeaderUtil.isKeepAlive(request)){
			future.addListener(new GenericFutureListener<Future<? super Void>>() {
				@SuppressWarnings("rawtypes")
				@Override
				public void operationComplete(Future  future) throws Exception {
					ctx.close();
				}
			});
		}
	}

	private void dobusiness(Order order) {
		Address address = new Address();
		address.setCity("北京市");
		address.setCountry("中国");
		address.setPostCode("654321");
		address.setState("北京市");
		address.setStreet1("朝阳区朝外街");
		
		List<String> midName = new ArrayList<String>();
		midName.add("李元芳");
		
		order.getCustomer().setFirstName("狄");
		order.getCustomer().setLastName("仁杰");
		order.getCustomer().setMiddleNames(midName);
		
		
		order.setBillto(address);
		order.setShipto(address);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		if (ctx.channel().isActive()) {
			sendError(ctx, HttpResponseStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,status,Unpooled.copiedBuffer("失败: "+status.toString()+"\r\n",CharsetUtil.UTF_8 ));
		response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset=UTF-8");
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}

}
