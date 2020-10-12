package com.huyong.study.rpc.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-09-10 10:23 下午
 */
public class ConsumerHandler extends ChannelInboundHandlerAdapter {
    private Object msg;

    public Object getMsg() {
        return msg;
    }

    public void setMsg(final Object msg) {
        this.msg = msg;
    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg) throws Exception {
        this.msg = msg;
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) throws Exception {
        cause.printStackTrace();
    }
}
