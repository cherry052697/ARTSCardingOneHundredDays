package com.cherry.netty.test.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.sql.Date;

public class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {

	private AsynchronousSocketChannel channel;
	
	
	public ReadCompletionHandler(AsynchronousSocketChannel channel) {
		if(this.channel == null){
			this.channel = channel;
		}
	}

	@Override
	public void completed(Integer result, ByteBuffer attachment) {
		attachment.flip();
		byte[] body = new byte[attachment.remaining()];
		attachment.get(body);
		try {
			String req = new String(body, "UTF-8");
			System.out.println("the aio time server receive order : "+req);
			String currentTime = "query time order".equalsIgnoreCase(req)?new Date(System.currentTimeMillis()).toString():"bad order";
			doWrite(currentTime);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void doWrite(String currentTime) {
		if(currentTime != null && currentTime.trim().length() > 0){
			byte[] bytes = currentTime.getBytes();
			ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
			writeBuffer.put(bytes);
			writeBuffer.flip();
			channel.write(writeBuffer,writeBuffer,new CompletionHandler<Integer, ByteBuffer>() {

				@Override
				public void completed(Integer result, ByteBuffer attachment) {
					if(attachment.hasRemaining()){
						channel.write(attachment, attachment, this);
					}
				}

				@Override
				public void failed(Throwable exc, ByteBuffer attachment) {
					try {
						channel.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
	}

	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
		try {
			this.channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
