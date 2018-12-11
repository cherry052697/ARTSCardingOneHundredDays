package com.cherry.netty.demo.httpxml;

import java.util.List;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;

public class HttpXmlRequestDecoder extends AbstractHttpXmlDecoder<FullHttpRequest> {
	
	public HttpXmlRequestDecoder(Class<?> clazz){
		this(clazz,false);
	}
	
	public HttpXmlRequestDecoder(Class<?> clazz, boolean isPrint) {
		super(clazz,isPrint);
	}

	@Override
	protected void decode(ChannelHandlerContext arg0, FullHttpRequest arg1, List<Object> arg2) throws Exception {
		if(!arg1.decoderResult().isSuccess()){
			sendError(arg0,HttpResponseStatus.BAD_REQUEST);
			return ;
		}
		HttpXmlRequest request = new HttpXmlRequest(arg1, decode0(arg0,arg1.content()));
		arg2.add(request);
	}

	private void sendError(ChannelHandlerContext arg0, HttpResponseStatus badRequest) {
		// TODO Auto-generated method stub
		
	}

	

}
