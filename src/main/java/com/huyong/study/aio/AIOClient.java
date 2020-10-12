package com.huyong.study.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-08-16 9:43 下午
 */
public class AIOClient {
    private final AsynchronousSocketChannel client;

    public AIOClient() throws IOException {
        this.client = AsynchronousSocketChannel.open();
    }
    public void connect(String host, int port) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        client.connect(new InetSocketAddress(host, port), null, new CompletionHandler<Void, Void>() {
            @Override
            public void completed(final Void result, final Void attachment) {
                try {
                    countDownLatch.countDown();
                    client.write(ByteBuffer.wrap("这是一条测试数据".getBytes())).get();
                    System.out.println("已发送到服务端");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(final Throwable exc, final Void attachment) {
                exc.printStackTrace();
            }
        });
        countDownLatch.await();
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        client.read(allocate, null, new CompletionHandler<Integer, Object>() {
            @Override
            public void completed(final Integer result, final Object attachment) {
                System.out.println("IO操作完成" + result);
                System.out.println("获取反馈结果" + new String(allocate.array()));
            }

            @Override
            public void failed(final Throwable exc, final Object attachment) {
                exc.printStackTrace();
            }
        });
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new AIOClient().connect("localhost", 8080);
    }
}
