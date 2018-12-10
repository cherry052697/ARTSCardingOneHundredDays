package com.example.demo.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestNIO {
	public static void main(String[] args) throws IOException {
		TestNIO tn = new TestNIO();
//		tn.readFileChanneltoBuffer();
		tn.readFromBuffer();
	}
	
	public void readFromBuffer() throws IOException{
		RandomAccessFile aFile = new RandomAccessFile("C:/Users/Administrator/Desktop/index.html", "rw");
		FileChannel inChannel = aFile.getChannel();

		//create buffer with capacity of 48 bytes
		ByteBuffer buf = ByteBuffer.allocate(48);

		int bytesRead = inChannel.read(buf); //read into buffer.
		while (bytesRead != -1) {

		  buf.flip();  //make buffer ready for read

		  while(buf.hasRemaining()){
		      System.out.print((char) buf.get()); // read 1 byte at a time
		  }

		  buf.clear(); //make buffer ready for writing
		  bytesRead = inChannel.read(buf);
		}
		aFile.close();
	}

	public void readFileChanneltoBuffer() throws IOException {
		RandomAccessFile aFile = new RandomAccessFile("C:/Users/Administrator/Desktop/index.html", "rw");
		FileChannel inChannel = aFile.getChannel();

		ByteBuffer buf = ByteBuffer.allocate(48);

		int bytesRead = inChannel.read(buf);
		while (bytesRead != -1) {

			System.out.println("Read " + bytesRead);
			buf.flip();

			while (buf.hasRemaining()) {
				System.out.print((char) buf.get());
			}

			buf.clear();
			bytesRead = inChannel.read(buf);
		}
		aFile.close();
	}
}
