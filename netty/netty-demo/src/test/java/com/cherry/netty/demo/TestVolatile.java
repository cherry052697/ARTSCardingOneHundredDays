package com.cherry.netty.demo;

import java.util.concurrent.TimeUnit;

public class TestVolatile {
	private volatile static boolean stop;
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int i = 0;
				while(!stop){
					i++;
					System.out.println("i="+i);
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		t.start();
		TimeUnit.SECONDS.sleep(3);
		stop = true;
	}

}
