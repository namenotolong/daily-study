package com.huyong.bigdata;


import java.util.StringTokenizer;

public class Test {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        StringTokenizer sd = new StringTokenizer("ssss\td");
        System.out.println(sd);
        System.out.println(sd.nextElement());
        System.out.println(sd.toString());
    }
}
