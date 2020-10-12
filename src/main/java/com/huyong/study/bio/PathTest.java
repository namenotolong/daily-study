package com.huyong.study.bio;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-07-09 2:22 下午
 */
public class PathTest {
    public static void main(String[] args) {
        Path path = Paths.get("./test.txt");
        System.out.println(path.getFileName());
        System.out.println(path.getFileSystem());
        System.out.println(path.getName(1));
        System.out.println(path.getNameCount());
        System.out.println(path.getRoot());
        System.out.println(path.getParent());

    }
}
