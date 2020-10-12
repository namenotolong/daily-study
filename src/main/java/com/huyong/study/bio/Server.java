package com.huyong.study.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-06-17 9:00 下午
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        Socket accept = server.accept();
        InputStream inputStream = accept.getInputStream();
        new BufferedReader(new InputStreamReader(inputStream)).lines().forEach(System.out::println);
    }
}
