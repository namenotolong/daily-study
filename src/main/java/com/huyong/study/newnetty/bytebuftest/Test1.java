package com.huyong.study.newnetty.bytebuftest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledHeapByteBuf;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-07-22 10:34 下午
 */
public class Test1 {
    public static void main(String[] args) {
        Charset utf8 = StandardCharsets.UTF_8;
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        final boolean b = buf.hasArray();
        System.out.println(b);
        System.out.println((char)buf.getByte(0));

        int readerIndex = buf.readerIndex();
        System.out.println(readerIndex);
        int writerIndex = buf.writerIndex();
        System.out.println(writerIndex);

        buf.setByte(0, (byte)'B');

        System.out.println((char)buf.getByte(0));
        assert readerIndex == buf.readerIndex();
        assert writerIndex ==  buf.writerIndex();
        buf.readByte();
        System.out.println(buf.readerIndex());
        final ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(1);
    }
}
