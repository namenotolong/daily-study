package com.huyong.study.netty;

import io.netty.buffer.*;

import java.nio.charset.StandardCharsets;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-09-11 9:57 下午
 */
public class Test {
    public static void main(String[] args) {
        ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer();
        byteBuf.writeCharSequence("hello world", StandardCharsets.UTF_8);
        System.out.println(byteBuf.readCharSequence(255, StandardCharsets.UTF_8));
        ByteBuf buffer = Unpooled.buffer();
        //ByteBufHolder byteBufHolder = new ByteBufHolder();
        ByteBuf buffer1 = ByteBufAllocator.DEFAULT.buffer();
    }
}
