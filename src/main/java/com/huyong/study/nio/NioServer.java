package com.huyong.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-07-19 10:40 下午
 */
public class NioServer {
    static ByteBuffer allocate = ByteBuffer.allocate(1024);
    static ByteBuffer readBuffer = ByteBuffer.allocate(1024);
    static {
        allocate.put("hello this is back".getBytes(StandardCharsets.UTF_8));
        allocate.flip();
    }
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        ServerSocket socket = serverChannel.socket();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8080);
        socket.bind(inetSocketAddress);
        Selector selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer wrap = ByteBuffer.wrap("hello world".getBytes());
        while (true) {
            try {
                int select = selector.select();
                System.out.println("selector");
                //selector.selectNow();
                if (select == 0) {
                    continue;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    try {
                        if (!key.isValid()) {
                            System.out.println("非法");
                        }
                        if (key.isAcceptable()) {
                            System.out.println("链接请求来了");
                            SocketChannel client = serverChannel.accept();
                            client.configureBlocking(false);
                            client.register(selector,
                                    SelectionKey.OP_WRITE /*| SelectionKey.OP_READ*/, wrap.duplicate());
                            System.out.println("client from" + client);
                        }
                        SelectableChannel channel = key.channel();
                        if (! (channel instanceof SocketChannel)) {
                            continue;
                        }
                        SocketChannel tempChannel = (SocketChannel) key.channel();
                        if (key.isValid() && key.isReadable()) {
                            readBuffer.clear();
                            while (true) {
                                int read = tempChannel.read(readBuffer);
                                if (read == -1) {
                                    //关闭连接
                                    key.channel().close();
                                    key.cancel();
                                    break;
                                }
                                if (read <= 0) {
                                    break;
                                }
                                readBuffer.flip();
                                while (readBuffer.hasRemaining()) {
                                    System.out.print((char)readBuffer.get());
                                }
                                readBuffer.clear();
                            }
                        }
                        if (key.isValid() && key.isWritable()) {
                            /*allocate.clear();
                            allocate.put("hello this is back".getBytes(StandardCharsets.UTF_8));
                            allocate.flip();
                            tempChannel.write(allocate);*/
                            System.out.println(1);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        try {
                            key.channel().close();
                            key.cancel();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
