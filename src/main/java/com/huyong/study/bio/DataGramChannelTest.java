package com.huyong.study.bio;

import com.huyong.study.utils.IOUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-07-09 1:41 下午
 */
public class DataGramChannelTest {
    public static void main(String[] args) throws IOException {
        DatagramChannel open = DatagramChannel.open();
        open.socket().connect(new InetSocketAddress(9999));
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.clear();
        open.read(buffer);
        IOUtils.writeData("./test.txt", buffer);
    }
}
