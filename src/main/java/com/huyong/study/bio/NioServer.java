package com.huyong.study.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-07-19 10:40 下午
 */
public class NioServer {
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
                if (select == 0) {
                    continue;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    try {
                        if (key.isAcceptable()) {
                            SocketChannel client = serverChannel.accept();
                            client.configureBlocking(false);
                            client.register(selector,
                                    SelectionKey.OP_WRITE | SelectionKey.OP_READ, wrap.duplicate());
                            System.out.println("client from" + client);
                        }
                        if (key.isWritable()) {

                        }
                        if (key.isReadable()) {
                            SocketChannel tempChannel = (SocketChannel) key.channel();
                            ByteBuffer allocate = ByteBuffer.allocate(1024);
                            while (true) {
                                int read = tempChannel.read(allocate);
                                if (read == 0) {
                                    break;
                                }
                                allocate.flip();
                                while (allocate.hasRemaining()) {
                                    System.out.print((char)allocate.get());
                                }
                                allocate.clear();
                            }
                        }
                    } catch (Exception e) {
                        key.cancel();
                        try {
                            key.channel().close();
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
