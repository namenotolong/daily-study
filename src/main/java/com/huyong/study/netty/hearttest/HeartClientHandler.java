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
public class HeartClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(HeartClientHandler.class);

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        logger.info("from client client receive close channel msg");
        ctx.channel().close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        logger.info("from client client receive server msg:{}", msg);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        if (evt instanceof IdleStateEvent) {
            logger.debug("from client send heart beat msg...");
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error("from client exceptionCaught : {}", cause.getMessage(), cause);
        ctx.channel().close();
    }
}
