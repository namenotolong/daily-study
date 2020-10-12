package com.huyong.study.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-08-17 9:45 下午
 */
public class MappedCopyFile {
    public static void main(String[] args) throws IOException {
        List<String> list = Files.readAllLines(Paths.get("/Users/weidian/Desktop/temp.html"));
        System.out.println(list);
        String fileName = "/Users/weidian/Desktop/temp.html";
        String outName = "/Users/weidian/Desktop/out.html";

        RandomAccessFile rw = new RandomAccessFile(fileName, "rw");
        FileChannel channel = rw.getChannel();
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);
        map.put(0, (byte)97);
        map.put(1023, (byte)122);
        rw.close();
    }
}
