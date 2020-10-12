package com.huyong.study.bio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-07-09 2:30 下午
 */
public class FilesTest {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("./test.txt");
        boolean exists = Files.exists(path);
        System.out.println(exists);
        Files.newBufferedReader(path).lines().forEach(System.out :: println);
    }
}
