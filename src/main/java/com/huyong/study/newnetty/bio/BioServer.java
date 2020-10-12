package com.huyong.study.newnetty.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-07-22 8:23 下午
 */
public class BioServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            Socket accept = serverSocket.accept();
            //读
            new Thread(() -> {
                try {
                    InputStream inputStream = accept.getInputStream();
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buf)) != -1) {
                        System.out.println(new String(buf, 0, len));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            //写
            new Thread(() -> {
                try {
                    OutputStream outputStream = accept.getOutputStream();
                    Scanner scanner = new Scanner(System.in);
                    while (scanner.hasNextLine()) {
                        String s = scanner.nextLine();
                        outputStream.write(s.getBytes());
                        outputStream.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
