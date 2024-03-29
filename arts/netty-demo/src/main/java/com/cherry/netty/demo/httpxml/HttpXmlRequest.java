package com.cherry.netty.demo.httpxml;

import io.netty.handler.codec.http.FullHttpRequest;

public class HttpXmlRequest {
	private FullHttpRequest request;
	private Object body;
	public HttpXmlRequest(FullHttpRequest request, Object body) {
		this.request = request;
		this.body = body;
	}
	
	public final FullHttpRequest getRequest(){
		return request;
	}
	
	public final void setRequest(FullHttpRequest request){
		this.request = request;
	}
	
	public final Object getBody(){
		return body;
	}

	public final void setBody(Object body){
		this.body= body;
	}
}
