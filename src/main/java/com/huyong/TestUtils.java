package com.huyong;


import java.io.*;
import java.util.stream.Collectors;

public class TestUtils {
    public static void printArgs(String ... args){
        for(Object elem: args){
            System.out.println(elem + " ");
        }
    }

    public static void test() throws IOException {
        File file = new File("/Users/weidian/Desktop/temptemp.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        bufferedWriter.write("hello world");
        bufferedWriter.flush();
        bufferedWriter.close();
        String collect = new BufferedReader(new InputStreamReader(new FileInputStream(file))).lines().collect(Collectors.joining());
        System.out.println(collect);
    }

    public static void main(String[] args) throws IOException {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (final StackTraceElement stackTraceElement : stackTrace) {
            System.out.println(stackTraceElement.getClassName() + ","
            + stackTraceElement.getFileName() + "," + stackTraceElement.getMethodName()
                    + "," + stackTraceElement.getLineNumber());
        }
        //test();
        testType();
    }

    public static void testType() {
        System.out.println(Integer.TYPE);
    }
}
