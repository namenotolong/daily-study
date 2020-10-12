package com.huyong.study.newnetty.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-07-22 8:14 下午
 */
public class BioClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);
        //写
        new Thread(() -> {
            try(OutputStream outputStream = socket.getOutputStream()) {
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextLine()) {
                    String s = scanner.nextLine();
                    if ("bye".equals(s)) {
                        break;
                    }
                    outputStream.write(s.getBytes());
                    outputStream.flush();
                }
                scanner.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        //读
        new Thread(() -> {
            try(InputStream inputStream = socket.getInputStream()) {
                byte[] buf = new byte[1024];
                int len;
                while ((len = inputStream.read(buf)) != -1) {
                    System.out.println(new String(buf, 0, len));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
