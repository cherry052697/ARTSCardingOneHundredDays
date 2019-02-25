package com.cherry.algorithm;

public enum EdgeStatus {
	UNDETERMINED((byte) 1), TREE((byte) 2), CROSS((byte) 3), FORWARD((byte) 3), BACKWARD((byte) 3),;

	public byte value;

	EdgeStatus(byte v) {
		this.value = v;
	}

	public byte value() {
		return value;
	}
}
