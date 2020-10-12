package com.huyong.study.bio;

import com.huyong.study.utils.IOUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-07-09 2:00 下午
 */
public class PipeTest {
    public static void main(String[] args) throws IOException {
        Pipe open = Pipe.open();
        Pipe.SinkChannel sink = open.sink();
        String newData = "New String to write to file..." + System.currentTimeMillis();
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());

        buf.flip();

        while(buf.hasRemaining()) {
            sink.write(buf);
        }
        Pipe.SourceChannel source = open.source();
        ByteBuffer buffer = ByteBuffer.allocate(48);
        int read = source.read(buffer);
        System.out.println(read);
        buffer.flip();

        IOUtils.writeData("./test.txt", buffer);
        String read1 = IOUtils.read("./test.txt");
        System.out.println(read1);
    }
}
