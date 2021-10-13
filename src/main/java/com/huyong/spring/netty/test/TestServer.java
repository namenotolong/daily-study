package com.huyong.spring.netty.test;

import com.huyong.spring.netty.NettyUtils;

/**
 * @author huyong
 */
public class TestServer {
    public static void main(String[] args) throws InterruptedException {
        NettyUtils.startServer(8080);
    }
}
