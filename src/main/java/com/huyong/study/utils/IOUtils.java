package com.huyong.study.utils;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.util.stream.Collectors;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-07-09 10:35 上午
 */
public class IOUtils {
    public static void close(Closeable ...closeable) {
        for (Closeable closeable1 : closeable) {
            if (closeable1 != null) {
                try {
                    closeable1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void writeData(String path, ByteBuffer buffer) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(path, "rw");
            FileChannel channel = randomAccessFile.getChannel();
            channel.write(buffer);
            channel.force(true);
            channel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendDatagram(String data, String url, int port, DatagramChannel channel) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.clear();
        buffer.put(data.getBytes());
        buffer.flip();
        try {
            channel.send(buffer, new InetSocketAddress(url, port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String read(String path) {
        StringBuilder sb = new StringBuilder();
        FileInputStream fileInputStream = null;
        FileChannel channel = null;
        try {
            fileInputStream = new FileInputStream(path);
            channel = fileInputStream.getChannel();
            ByteBuffer allocate = ByteBuffer.allocate(100);
            try {
                while (channel.read(allocate) != -1) {
                    allocate.flip();
                    while (allocate.hasRemaining()) {
                        sb.append((char)allocate.get());
                    }
                    allocate.clear();
                }
                return sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            close(fileInputStream, channel);
        }
        return sb.toString();
    }

    public static String read(InputStream inputStream) {
        if (inputStream != null) {
            return new BufferedReader(new InputStreamReader(inputStream))
                    .lines()
                    .collect(Collectors.joining());
        }
        return null;
    }
}
