package com.huyong.study.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-08-16 9:30 下午
 */
public class AIOServer {
    private final int port;

    public AIOServer(int port){
        this.port = port;
        listen();
    }

    public static void main(String[] args) {
        int port = 8080;
        new AIOServer(port);
    }

    public void listen() {
        try {
            ExecutorService executorService = Executors.newCachedThreadPool();
            AsynchronousChannelGroup asynchronousChannelGroup =
                    AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);
            AsynchronousServerSocketChannel open =
                    AsynchronousServerSocketChannel.open(asynchronousChannelGroup);
            open.bind(new InetSocketAddress(port));
            System.out.println("服务启动，监听端口：" + port);
            open.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
                ByteBuffer buf = ByteBuffer.allocateDirect(1024);
                @Override
                public void completed(final AsynchronousSocketChannel result, final Object attachment) {
                    System.out.println("IO操作成功，开始获取数据");
                    try {
                        buf.clear();
                        result.read(buf).get();
                        buf.flip();
                        result.write(buf);
                        buf.flip();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            result.close();
                            open.accept(null, this);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("操作完成");
                }

                @Override
                public void failed(final Throwable exc, final Object attachment) {
                    System.out.println("IO操作失败");
                    exc.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.MILLISECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
