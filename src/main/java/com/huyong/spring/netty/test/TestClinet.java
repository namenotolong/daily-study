package com.huyong.spring.netty.test;

import com.huyong.spring.netty.NettyUtils;

/**
 * @author huyong
 */
public class TestClinet {
    public static void main(String[] args) throws InterruptedException {
        NettyUtils.send(8080, "localhost", "hello world");
    }
}
