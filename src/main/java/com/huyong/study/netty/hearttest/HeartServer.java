package com.huyong.study.netty.hearttest;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public class HeartServer {

    private int port = 8080;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void start() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup master = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        HeartServerHandler heartServerHandler = new HeartServerHandler();

        serverBootstrap.group(master, worker)
                // 指定 Channel 为服务端 NioServerSocketChannel
                .channel(NioServerSocketChannel.class)
                // 设置 Netty Server 的端口
                .localAddress(new InetSocketAddress(port))
                // 服务端 accept 队列的大小
                .option(ChannelOption.SO_BACKLOG, 1024)
                // TCP Keepalive 机制，实现 TCP 层级的心跳保活功能
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                // 允许较小的数据包的发送，降低延迟
                .childOption(ChannelOption.TCP_NODELAY, true)

                .childHandler(heartServerHandler);
        // 绑定端口，并同步等待成功，即启动服务端
        ChannelFuture future = null;
        try {
            future = serverBootstrap.bind(port).sync();
            if (future.isSuccess()) {
                logger.info("[start][Netty Server 启动在 {} 端口]", port);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new HeartServer().start();

    }

}
