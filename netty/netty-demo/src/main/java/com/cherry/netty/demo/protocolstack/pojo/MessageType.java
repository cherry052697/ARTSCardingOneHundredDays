package com.cherry.netty.demo.protocolstack.pojo;

public enum MessageType {
	LOGIN_REQ((byte) 1), LOGIN_RESP((byte) 2), HEARTBEAT_REQ((byte) 3), HEARTBEAT_RESP((byte) 4),;

	public byte value;

	MessageType(byte v) {
		this.value = v;
	}

	public byte value() {
		return value;
	}

}
