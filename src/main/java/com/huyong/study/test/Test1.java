package com.huyong.study.test;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-08-28 1:51 下午
 */
public class Test1 {
    public static void main(String[] args) {
        List<String> list = Collections.singletonList("hello");
        System.out.println(list.getClass());
        Object[] objects = list.toArray();
        System.out.println(objects.getClass());
        objects[0] = new Object();
    }
}
