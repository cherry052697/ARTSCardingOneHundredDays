package com.cherry.algorithm;

public enum DistType {
	INFINITY((byte) 1), LOGIN_RESP((byte) 2), HEARTBEAT_REQ((byte) 3), HEARTBEAT_RESP((byte) 4),;

	public byte value;

	DistType(byte v) {
		this.value = v;
	}

	public byte value() {
		return value;
	}
}
