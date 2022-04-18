package com.huyong.study.netty.hearttest;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
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

        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();

        bootstrap.group(eventExecutors)
                .channel(NioSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ch.pipeline()
                                //.addLast("client-idle-handler", new IdleStateHandler(3000, 0, 0, TimeUnit.MILLISECONDS))
                                .addLast(new HeartClientHandler());
                    }
                });
    }


    public void sendMsg(String msg) {
        try {
            if (channel == null) {
                ChannelFuture connect = bootstrap.connect(new InetSocketAddress(8080));
                ChannelFuture sync = connect.sync();
                if (!sync.isSuccess()) {
                    logger.error("connect remote error");
                    throw new RuntimeException("channel connect error");
                }
                channel = sync.channel();
            }
            channel.writeAndFlush(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
