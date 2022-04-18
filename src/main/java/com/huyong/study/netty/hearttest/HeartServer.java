package com.huyong.study.netty.hearttest;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class HeartServer {

    private int port = 8080;

    public void start() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup master = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        serverBootstrap.group(master, worker)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler())
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ch.pipeline()
                                //.addLast("server-idle-handle", new IdleStateHandler(0, 0, 20000, TimeUnit.MILLISECONDS))
                                .addLast("handler", new HeartServerHandler());
                    }
                });
        try {
            ChannelFuture future = serverBootstrap.bind(port).sync();
            System.out.println(future.isSuccess());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        new HeartServer().start();

    }

}
