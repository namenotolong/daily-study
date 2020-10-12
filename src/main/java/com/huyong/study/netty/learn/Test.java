package com.huyong.study.netty.learn;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-09-13 11:44 上午
 */
public class Test {
    public static void testFrameLength() {
        ByteBuf buffer = Unpooled.buffer();
        for (int i = 0; i < 9; i++) {
            buffer.writeByte(i);
        }
        ByteBuf duplicate = buffer.duplicate();
        EmbeddedChannel channel = new EmbeddedChannel(new DecoderTest(3));
        System.out.println(channel.writeInbound(duplicate.retain()));
        System.out.println(channel.finish());
        ByteBuf byteBuf = channel.readInbound();
        System.out.println(buffer.readSlice(3).equals(byteBuf));
        byteBuf.release();
        byteBuf = channel.readInbound();
        System.out.println(buffer.readSlice(3).equals(byteBuf));
        byteBuf.release();
        byteBuf = channel.readInbound();
        System.out.println(buffer.readSlice(3).equals(byteBuf));
        byteBuf.release();
    }

    public static void testEncode() {
        ByteBuf buffer = Unpooled.buffer();
        for (int i = 0; i < 10; i++) {
            buffer.writeInt(-1 * i);
        }
        EmbeddedChannel channel = new EmbeddedChannel(new EncoderTest());
        System.out.println(channel.writeOutbound(buffer));
        System.out.println(channel.finish());
        for (int i = 0; i < 10; i++) {
            Object o = channel.readOutbound();
            System.out.println(o);
        }
        Object o = channel.readOutbound();
        System.out.println(o);
    }

    public static void main(String[] args) {
        testEncode();
    }
}
