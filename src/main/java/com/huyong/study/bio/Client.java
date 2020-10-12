package com.huyong.study.bio;

import com.huyong.study.utils.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-07-19 11:27 下午
 */
public class Client {
    public static void main(String[] args) throws IOException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 8080);
        Socket socket = new Socket();
        socket.connect(inetSocketAddress);

        OutputStream outputStream = socket.getOutputStream();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            outputStream.write(s.getBytes());
            outputStream.flush();
        }
    }
}
