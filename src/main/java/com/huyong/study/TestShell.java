package com.huyong.study;

import java.io.*;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-07-08 7:48 下午
 */
public class TestShell {
    public static void main(String[] args) throws IOException, InterruptedException {
        Process exec = Runtime.getRuntime().exec("sh test.sh");
        InputStream inputStream1 = exec.getInputStream();
        new Thread(() -> {
            new BufferedReader(new InputStreamReader(inputStream1)).lines().forEach(System.out :: println);
        }).start();
        exec.waitFor();
    }
}
