package com.cherry.netty.demo;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;

public class TestBuffers {
	public static void main(String[] args) {
		int loop = 3000000;
		long startTime = System.currentTimeMillis();
		ByteBuf poolBuffer = null;
		for (int i=0;i<loop;i++) {
			poolBuffer = PooledByteBufAllocator.DEFAULT.directBuffer(1024);
			poolBuffer.writeBytes((String.valueOf(i)).getBytes());
			poolBuffer.release();
		}
		System.out.println("PooledByteBufAllocator.DEFAULT.directBuffer(1024) 300w times cost time is : "+(System.currentTimeMillis()-startTime));
		
		long startTime2 = System.currentTimeMillis();
		ByteBuf uppoolBuffer = null;
		for (int i=0;i<loop;i++) {
			uppoolBuffer = Unpooled.directBuffer(1024);
			uppoolBuffer.writeBytes((String.valueOf(i)).getBytes());
		}
		System.out.println("Unpooled.directBuffer(1024) 300w times cost time is : "+(System.currentTimeMillis()-startTime2));

	}

}
