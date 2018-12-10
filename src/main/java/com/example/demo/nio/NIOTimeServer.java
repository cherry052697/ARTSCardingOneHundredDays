package com.example.demo.nio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



public class NIOTimeServer {
	@SuppressWarnings("null")
	public static void main(String[] args) throws IOException {
		int port = 8080;
		if(args != null && args.length>0){
			port = Integer.valueOf(args[0]);
		}
		MutiplexerTimeServer timeServer = new MutiplexerTimeServer(port);
		new Thread(timeServer, "NIO-MutiplexerTimeServer-001").start();
	}

}
