package com.cherry.netty.demo.httpxml;

import java.util.List;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;

public class HttpXmlResponseDecoder extends AbstractHttpXmlDecoder<DefaultFullHttpResponse> {

	protected HttpXmlResponseDecoder(Class<?> clazz) {
		this(clazz,false);
	}

	protected HttpXmlResponseDecoder(Class<?> clazz, boolean b) {
		super(clazz, b);
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, DefaultFullHttpResponse msg, List<Object> out) throws Exception {
		HttpXmlResponse resHttpXmlResponse = new HttpXmlResponse(msg, decode2Object(ctx, msg.content()));
		out.add(resHttpXmlResponse);
	}

}
