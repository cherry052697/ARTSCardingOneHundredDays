package com.cherry.netty.demo.protocolstack.loginhandler;

import org.apache.log4j.Logger;

import com.cherry.netty.demo.protocolstack.pojo.Header;
import com.cherry.netty.demo.protocolstack.pojo.MessageType;
import com.cherry.netty.demo.protocolstack.pojo.NettyMessage;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class LoginAuthReqHandler extends ChannelHandlerAdapter {
	
	private static final Logger logger = Logger.getLogger(LoginAuthReqHandler.class);
	
//    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(buildLoginReq());
    }

    
//    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        NettyMessage message = (NettyMessage) msg;

//        System.out.println("LoginAuthReqHandler.message.getHeader().getType()="+message.getHeader().getType());
        // 如果是握手应答消息，需要判断是否认证成功
        if (message.getHeader() != null
                && message.getHeader().getType() == MessageType.LOGIN_RESP.value()) {
            byte loginResult = (byte) message.getBody();
            if (loginResult != (byte) 0) {
                // 握手失败，关闭连接
                ctx.close();
            } else {
            	logger.info("Login is ok : " + message);
                ctx.fireChannelRead(msg);
            }
        } else
            //调用下一个channel链..
            ctx.fireChannelRead(msg);
    }

    /**
     * 构建登录请求
     */
    private NettyMessage buildLoginReq() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGIN_REQ.value());
        message.setHeader(header);
        return message;
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.fireExceptionCaught(cause);
    }

}
