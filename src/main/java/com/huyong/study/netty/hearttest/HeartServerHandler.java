package com.huyong.study.netty.hearttest;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huyong
 */
@ChannelHandler.Sharable
public class HeartServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(HeartServerHandler.class);

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        logger.info("from server channel close");
        ctx.channel().close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        logger.info("from server client connect success !" + ctx.channel().remoteAddress());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        logger.info("from server receive msg info:{}", msg);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            logger.debug("from server IdleStateEvent triggered, send heartbeat to channel " + ctx.channel());
            ctx.channel().close();
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error("from server exceptionCaught : {}", cause.getMessage(), cause);
        ctx.channel().close();
    }


}
