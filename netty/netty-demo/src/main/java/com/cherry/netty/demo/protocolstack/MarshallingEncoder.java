package com.cherry.netty.demo.protocolstack;




import java.io.IOException;

import org.jboss.marshalling.ByteOutput;
import org.jboss.marshalling.Marshaller;

import io.netty.buffer.ByteBuf;

public class MarshallingEncoder {
	
	private static final byte[] LENGTH_PLACEHOLDER = new byte[4];
	Marshaller marshaller;
	
	
	public MarshallingEncoder() throws IOException {
		marshaller = (Marshaller) MarshallingCodeCFactory.buildMarshalling();
	}


	protected void encode(Object msg, ByteBuf out) throws IOException {
		try {
			int lengthPos = out.writerIndex();
			out.writeBytes(LENGTH_PLACEHOLDER);
			ByteOutput output = new ChannelBufferByteOutput(out);
			marshaller.start(output);
			marshaller.writeObject(msg);
			marshaller.finish();
			out.setInt(lengthPos, out.writerIndex()-lengthPos-4);
		} finally {
			marshaller.close();
		}
	}

}
