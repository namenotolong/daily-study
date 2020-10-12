package com.huyong.study.netty.learn;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-09-13 11:41 上午
 */
public class DecoderTest extends ByteToMessageDecoder {

    private final int frameLength;

    public DecoderTest(int frameLength) {
        this.frameLength = frameLength;
    }

    @Override
    protected void decode(final ChannelHandlerContext ctx, final ByteBuf in, final List<Object> out) throws Exception {
        while (in.readableBytes() >= frameLength) {
            ByteBuf byteBuf = in.readBytes(frameLength);
            out.add(byteBuf);
        }
    }
}
