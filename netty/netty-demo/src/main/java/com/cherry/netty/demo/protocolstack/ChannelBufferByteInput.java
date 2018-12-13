package com.cherry.netty.demo.protocolstack;


import java.io.IOException;

import org.jboss.marshalling.ByteInput;

import io.netty.buffer.ByteBuf;


public class ChannelBufferByteInput implements ByteInput{


	private final ByteBuf buffer;
	
	public ChannelBufferByteInput(ByteBuf buf) {
		this.buffer = buf;
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int available() throws IOException {
		return buffer.readableBytes();
	}

	@Override
	public int read() throws IOException {
		if(buffer.isReadable()){
			return buffer.readByte()&0xff;
		}
		return -1;
	}

	@Override
	public int read(byte[] arg0) throws IOException {
		return read(arg0,0,arg0.length);
	}

	@Override
	public int read(byte[] dst, int dstIndex, int length) throws IOException {
		int avaiable = available();
		if(avaiable == 0){
			return -1;
		}
		length = Math.min(avaiable, length);
		buffer.readBytes(dst,dstIndex,length);
		return length;
	}

	@Override
	public long skip(long bytes) throws IOException {
		int readable = buffer.readableBytes();
		if(readable < bytes){
			bytes = readable;
		}
		buffer.readerIndex((int)(buffer.readerIndex()+bytes));
		return bytes;
	}

}
