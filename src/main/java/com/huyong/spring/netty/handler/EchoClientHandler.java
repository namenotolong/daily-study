package com.huyong.spring.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * @author huyong
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {
    private final ByteBuf firstMessage;

    public EchoClientHandler(){
        byte[] req = "Hello from client".getBytes();
        firstMessage = Unpooled.buffer(req.length);
        firstMessage.writeBytes(req);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client channel active");
        // Send the message to Server
        System.out.println("client send req...");
        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        try {
            String body = new String(req, StandardCharsets.UTF_8);
            System.out.printf("client channel read msg:%s \n", body);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        System.out.printf("client caught exception %s", cause);
        ctx.close();
    }

}
