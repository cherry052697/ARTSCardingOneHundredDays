package com.cherry.netty.demo.httpxml;


import java.io.StringReader;
import java.nio.charset.Charset;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

public abstract class AbstractHttpXmlDecoder<T> extends MessageToMessageDecoder<T> {
	private IBindingFactory factory;
	private StringReader reader;
	private Class<?> clazz;
	private boolean isPrint;
	private final static String CHARSET_NAME="UTF-8";
	private final static Charset UTF_8=Charset.forName(CHARSET_NAME);
	
	protected AbstractHttpXmlDecoder(Class<?> clazz){
		this(clazz, false);
	}
	
	protected AbstractHttpXmlDecoder(Class<?> clazz2, boolean isPrint2) {
		this.clazz = clazz2;
		this.isPrint = isPrint2;
	}
	
	protected Object decode2Object(ChannelHandlerContext arg0,ByteBuf body) throws JiBXException{
		factory = BindingDirectory.getFactory(clazz);
		String content = body.toString(UTF_8);
		if(isPrint)
			System.out.println("the body is : "+content);
		reader = new StringReader(content);
		IUnmarshallingContext uctx = factory.createUnmarshallingContext();
		Object result = uctx.unmarshalDocument(reader);
		reader.close();
		reader = null;
		return result;
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		if(reader != null){
			reader.close();
			reader = null;
		}
	}

	
}
