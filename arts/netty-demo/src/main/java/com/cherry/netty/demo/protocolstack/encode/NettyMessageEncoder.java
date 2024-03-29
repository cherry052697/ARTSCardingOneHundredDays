package com.cherry.netty.demo.protocolstack.encode;

import java.io.IOException;
import java.util.Map;

import com.cherry.netty.demo.protocolstack.pojo.NettyMessage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public final class NettyMessageEncoder extends MessageToByteEncoder<NettyMessage> {
	 MarshallingEncoder marshallingEncoder;

	    public NettyMessageEncoder() throws IOException {
	        this.marshallingEncoder = new MarshallingEncoder();
	    }

	    @SuppressWarnings("unused")
		@Override
	    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, ByteBuf sendBuf) throws Exception {
	        if (null == msg || null == msg.getHeader()) {
	            throw new Exception("The encode message is null");
	        }
	        sendBuf.writeInt(msg.getHeader().getCrcCode());
	        sendBuf.writeInt(msg.getHeader().getLength());
	        sendBuf.writeLong(msg.getHeader().getSessionID());
	        sendBuf.writeByte(msg.getHeader().getType());
	        sendBuf.writeByte(msg.getHeader().getPriority());
	        sendBuf.writeInt(msg.getHeader().getAttachment().size());

	        String key = null;
	        byte[] keyArray = null;
	        Object value = null;
	        for (Map.Entry<String, Object> param : msg.getHeader().getAttachment()
	                .entrySet()) {
	            key = param.getKey();
	            keyArray = key.getBytes("UTF-8");
	            sendBuf.writeInt(keyArray.length);
	            sendBuf.writeBytes(keyArray);
	            value = param.getValue();
	        }
	        key = null;
	        keyArray = null;
	        value = null;

	        if (msg.getBody() != null) {
	            marshallingEncoder.encode(msg.getBody(), sendBuf);
	        } else{
	        	sendBuf.writeInt(0);
	        }
	        // 之前写了crcCode 4bytes，除去crcCode和length 8bytes即为更新之后的字节
	        sendBuf.setInt(4, sendBuf.readableBytes() - 8);
	    }
}
