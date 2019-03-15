package com.cherry.algorithm;

public enum VertexStatus {
	UNDISCOVERED((byte) 1), DISCOVERED((byte) 2), VISTIED((byte) 3),;

	public byte value;

	VertexStatus(byte v) {
		this.value = v;
	}

	public byte value() {
		return value;
	}
}
