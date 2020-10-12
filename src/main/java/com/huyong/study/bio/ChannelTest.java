package com.huyong.study.bio;

import com.huyong.study.utils.IOUtils;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-07-08 10:31 下午
 */
public class ChannelTest {
    public static void main(String[] args) throws IOException {
        RandomAccessFile rw = new RandomAccessFile("./test.txt", "rw");
        FileChannel channel = rw.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(10);
        ServerSocketChannel open = ServerSocketChannel.open();
        open.socket().bind(new InetSocketAddress(9999));
        open.configureBlocking(false);
        while (true) {
            SocketChannel accept = open.accept();
            if (accept != null) {
                while (accept.read(buffer) != -1) {
                    channel.write(buffer);
                }
            }
        }
    }
}
