package com.huyong.spring.netty;

import com.huyong.spring.netty.handler.EchoClientHandler;
import com.huyong.spring.netty.handler.EchoServerHandler;
import com.huyong.spring.netty.handler.EchoServerHandler2;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author huyong
 */
public class NettyUtils {

    String s = new String("1");

    public static void main(String[] args) throws InterruptedException {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        buffer.writeBoolean(true);
        System.out.println(buffer.getBoolean(0));
        startServer(8080);
        //new NettyUtils().test();
    }

    public void test() throws InterruptedException {
        System.out.println(1);
        synchronized (s) {
            Thread.sleep(1000);
            test();
        }
    }

    public static void startServer(int port) throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, work)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline p = socketChannel.pipeline();
                        p.addLast(new EchoServerHandler());
                        p.addLast(new EchoServerHandler2());
                    }
                });
        ChannelFuture sync = bootstrap.bind(port).sync().addListener(future -> System.out.println(1));
        sync.channel().closeFuture().sync();
    }

    public static void send(int port, String host, String msg) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline p = socketChannel.pipeline();
                        p.addLast(new EchoClientHandler());
                    }
                });
        ChannelFuture sync = bootstrap.connect(host, port).sync();
        sync.channel().closeFuture().sync();
    }
}
