package com.huyong.study.bio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-07-09 2:35 下午
 */
public class AsynchronousFileChannelTest {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("./test.txt");
        AsynchronousFileChannel open = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        Future<Integer> read = open.read(allocate, 0);
        while (!read.isDone());
        allocate.flip();
        byte[] bytes = new byte[allocate.limit()];
        ByteBuffer buffer = allocate.get(bytes);
        System.out.println(new String(bytes));
    }
}
