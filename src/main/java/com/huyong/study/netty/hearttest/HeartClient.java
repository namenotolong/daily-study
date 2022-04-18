package com.huyong.study.netty.hearttest;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public class HeartClient {

    private final Logger logger = LoggerFactory.getLogger(HeartClient.class);

    Bootstrap bootstrap = new Bootstrap();

    Channel channel = null;

    public void start() {

        HeartClientHandler heartClientHandler = new HeartClientHandler();

        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();

        bootstrap.group(eventExecutors)
                // 指定 Channel 为客户端 NioSocketChannel
                .channel(NioSocketChannel.class)
                // 指定链接服务器的地址
                .remoteAddress("localhost", 8080)
                // TCP Keepalive 机制，实现 TCP 层级的心跳保活功能
                .option(ChannelOption.SO_KEEPALIVE, true)
                // 允许较小的数据包的发送，降低延迟
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ch.pipeline()
                                //.addLast("client-idle-handler", new IdleStateHandler(3000, 0, 0, TimeUnit.MILLISECONDS))
                                .addLast("encoder", new StringEncoder())
                                .addLast("decoder", new StringDecoder())
                                .addLast(heartClientHandler);
                    }
                });
        // 链接服务器，并异步等待成功，即启动客户端
        bootstrap.connect().addListener(new ChannelFutureListener() {

            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                // 连接失败
                if (!future.isSuccess()) {
                    logger.error("[start][Netty Client 连接服务器 失败]");
                    return;
                }
                // 连接成功
                channel = future.channel();
                logger.info("[start][Netty Client 连接服务器 成功]");
            }
        });
    }


    public void sendMsg(String msg) {
        try {
            if (channel == null) {
                logger.error("connect remote error");
                return;
            }
            ChannelFuture channelFuture = channel.writeAndFlush(msg);
            System.out.println(channelFuture.isSuccess());
            channelFuture.sync();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
