package com.huyong.study.netty.hearttest;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Test {
    public static void main(String[] args) {
        HeartClient heartClient = new HeartClient();
        heartClient.start();
        System.out.println("client start success");

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            heartClient.sendMsg(s);
        }
    }
}
