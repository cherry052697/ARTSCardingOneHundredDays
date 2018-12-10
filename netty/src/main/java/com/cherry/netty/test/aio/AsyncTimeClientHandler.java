package com.cherry.netty.test.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class AsyncTimeClientHandler implements CompletionHandler<Void, AsyncTimeClientHandler>, Runnable {

	
	private AsynchronousSocketChannel client;
	private String host;
	private int port;
	private CountDownLatch latch;
	

	public AsyncTimeClientHandler(String string, int port) {
		this.host = string;
		this.port = port;
		try {
			client = AsynchronousSocketChannel.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		latch = new CountDownLatch(1);
		client.connect(new InetSocketAddress(host, port), this, this);
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void completed(Void result, AsyncTimeClientHandler attachment) {
		byte[] req = "query time order".getBytes();
		ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
		writeBuffer.put(req);
		writeBuffer.flip();
		client.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {

			@Override
			public void completed(Integer result, ByteBuffer attachment) {
				// TODO Auto-generated method stub
				if (attachment.hasRemaining()) {
					client.read(attachment, attachment, this);
				}else{
					ByteBuffer readBuffer = ByteBuffer.allocate(1024);
					client.read(readBuffer, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {

						@Override
						public void completed(Integer result, ByteBuffer attachment) {
							// TODO Auto-generated method stub
							attachment.flip();
							byte[] bytes = new byte[attachment.remaining()];
							attachment.get(bytes);
							String body = null;
							try {
								body = new String(bytes, "UTF-8");
								System.out.println("now is :"+body);
								latch.countDown();
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
							
						}

						@Override
						public void failed(Throwable exc, ByteBuffer attachment) {
							// TODO Auto-generated method stub
							try {
								client.close();
								latch.countDown();
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
				// TODO Auto-generated method stub
				try {
					client.close();
					latch.countDown();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
	}

	@Override
	public void failed(Throwable exc, AsyncTimeClientHandler attachment) {
		// TODO Auto-generated method stub
		try {
			client.close();
			latch.countDown();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
