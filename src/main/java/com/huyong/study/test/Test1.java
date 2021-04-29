package com.huyong.study.test;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.lang.reflect.Array;
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
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> aClass = Class.forName("[Ljava.lang.String;");
        System.out.println(aClass);
        Object o = Array.newInstance(String.class, 10);
        System.out.println(o);
        ((String[])o)[0] = "hello";
        System.out.println(Arrays.toString((String[])o));
    }
}
class MyClassLoader extends ClassLoader{
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }
}
