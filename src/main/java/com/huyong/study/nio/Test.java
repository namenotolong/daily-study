package com.huyong.study.nio;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Iterator;
import java.util.List;

/**
 * @author huyong
 */
public class Test {
    public static void main(String[] args) throws IOException {
        List<String> list = Lists.newArrayList("1", "2");
        list.removeIf("1"::equals);
        System.out.println(list);
    }
}
