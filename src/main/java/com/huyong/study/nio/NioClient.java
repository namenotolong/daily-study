package com.huyong.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;


/**
 * @author huyong
 */
public class NioClient {
    public static void main(String[] args) {
        start();
    }
    public static void start() {
        try {
            Selector selector = Selector.open();
            SocketChannel open = SocketChannel.open();
            open.configureBlocking(false);
            doConnect(open, selector);
            selector.select(1000);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (!key.isValid()) {
                    continue;
                }
                SocketChannel channel = (SocketChannel) key.channel();
                if (key.isConnectable()) {
                    if (channel.finishConnect()) {
                        channel.register(selector, SelectionKey.OP_READ);
                        doWrite(channel);
                    } else {
                        System.out.println("链接失败");
                    }
                }
                if (key.isReadable()) {
                    ByteBuffer readBuf = ByteBuffer.allocate(1024);
                    int read = channel.read(readBuf);
                    if (read < 0) {
                        key.cancel();
                        channel.close();
                        continue;
                    }
                    if (read > 0) {
                        readBuf.flip();
                        byte[] arr = new byte[readBuf.remaining()];
                        readBuf.get(arr);
                        System.out.println(new String(arr));
                        continue;
                    }
                    channel.register(selector, SelectionKey.OP_READ);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void doConnect(SocketChannel socketChannel, Selector selector) throws IOException {
        //通过ip和端口号连接到服务器
        if (socketChannel.connect(new InetSocketAddress(8080))) {
            System.out.println("链接成功，注册op read，并写入数据");
            //向多路复用器注册可读事件
            socketChannel.register(selector, SelectionKey.OP_READ);
            //向管道写数据
            doWrite(socketChannel);
        } else {
            System.out.println("链接失败，注册op connect");
            //若连接服务器失败,则向多路复用器注册连接事件
            SelectionKey register = socketChannel.register(selector, SelectionKey.OP_CONNECT);
            System.out.println(register.isConnectable());
        }
    }

    public static void doWrite(SocketChannel channel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("hello world".getBytes(StandardCharsets.UTF_8));
        buffer.flip();
        channel.write(buffer);
    }
}
