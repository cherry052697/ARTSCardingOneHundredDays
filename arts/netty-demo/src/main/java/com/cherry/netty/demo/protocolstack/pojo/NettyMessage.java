package com.cherry.netty.demo.protocolstack.pojo;

import com.cherry.netty.utils.JsonUtil;

public final class NettyMessage {
	private Header header;
	private Object body;
	public final Header getHeader() {
		return header;
	}
	public final void setHeader(Header header) {
		this.header = header;
	}
	public final Object getBody() {
		return body;
	}
	public final void setBody(Object body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "NettyMessage[header="+JsonUtil.toJson(header)+"]";
	}
	
	

}
