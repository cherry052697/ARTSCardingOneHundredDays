package com.cherry.netty.demo.protocolstack;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;


import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class LoginAuthRespHandler extends ChannelHandlerAdapter {
	
	private static final Logger logger = Logger.getLogger(LoginAuthRespHandler.class.getName());
	    /**
	     * 本地缓存
	     */
	    private Map<String, Boolean> nodeCheck = new ConcurrentHashMap<String, Boolean>();
	    private String[] whitekList = {"127.0.0.1", "192.168.11.246","192.168.31.242"};

	    /**
	     * Calls {@link ChannelHandlerContext#fireChannelRead(Object)} to forward to
	     * the next {@link ChannelHandler} in the {@link ChannelPipeline}.
	     * <p>
	     * Sub-classes may override this method to change behavior.
	     */
	    @Override
	    public void channelRead(ChannelHandlerContext ctx, Object msg)
	            throws Exception {
	        NettyMessage message = (NettyMessage) msg;

	        // 如果是握手请求消息，处理，其它消息透传
	        if (message.getHeader() != null
	                && message.getHeader().getType() == MessageType.LOGIN_REQ.value()) {
	            String nodeIndex = ctx.channel().remoteAddress().toString();
	            NettyMessage loginResp = null;
	            // 重复登陆，拒绝
	            if (nodeCheck.containsKey(nodeIndex)) {
	                loginResp = buildResponse((byte) -1);
	            } else {
	                InetSocketAddress address = (InetSocketAddress) ctx.channel()
	                        .remoteAddress();
	                String ip = address.getAddress().getHostAddress();
	                boolean isOK = false;
	                for (String WIP : whitekList) {
	                    if (WIP.equals(ip)) {
	                        isOK = true;
	                        break;
	                    }
	                }
	                loginResp = isOK ? buildResponse((byte) 0)
	                        : buildResponse((byte) -1);
	                if (isOK)
	                    nodeCheck.put(nodeIndex, true);
	            }
	            logger.info("The login response is : " + loginResp+ " body [" + loginResp.getBody() + "]");
	            ctx.writeAndFlush(loginResp);
	        } else {
	            ctx.fireChannelRead(msg);
	        }
	    }

	    private NettyMessage buildResponse(byte result) {
	        NettyMessage message = new NettyMessage();
	        Header header = new Header();
	        header.setType(MessageType.LOGIN_RESP.value());
	        message.setHeader(header);
	        message.setBody(result);
	        return message;
	    }

	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
	            throws Exception {
	        cause.printStackTrace();
	        nodeCheck.remove(ctx.channel().remoteAddress().toString());// 删除缓存
	        ctx.close();
	        ctx.fireExceptionCaught(cause);
	    }
}
