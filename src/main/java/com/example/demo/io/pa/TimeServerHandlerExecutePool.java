package com.example.demo.io.pa;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;

public class TimeServerHandlerExecutePool {
	private ExecutorService executor;

	public TimeServerHandlerExecutePool(int maxPoolSize,int queueSize) {
		executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));
	}
	
	
	public void execute(Runnable task){
		executor.execute(task);
	}

}
