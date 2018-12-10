package com.example.demo.io.pa;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.example.demo.io.TimeServerHandler;

public class PseudoAsynchronousTimeServer {

	@SuppressWarnings("null")
	public static void main(String[] args) throws IOException {
		int port = 8080;
		if(args != null && args.length>0){
			port = Integer.valueOf(args[0]);
		}
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			System.out.println("server is starting...:"+port);
			Socket socket = null;
			// BIO
//			while(true){
//				socket = server.accept();
//				new Thread(new TimeServerHandler(socket)).start();
//			}
			// 伪异步I/O:创建一个时间服务器处理类的线程池，当接受到新的客户端连接时，将请求Socker封装成一个Task，
			// 然后调用线程池的二色cute方法执行，从而避免勒每个请求鸡儿都创建新的线程。
			TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50,10000);
			while(true){
				socket = server.accept();
				singleExecutor.execute(new TimeServerHandler(socket));
			}
			
		} finally {
			if(server == null){
				System.out.println("the time server close");
				server.close();
				server = null;
			}
		}
	}



}
