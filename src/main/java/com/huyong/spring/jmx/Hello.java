package com.huyong.spring.jmx;

public class Hello implements HelloMBean{
    @Override
    public String getName() {
        System.out.println("hello");
        return "hello";
    }

    @Override
    public int getAge() {
        System.out.println("getAge");
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(true ^ true);
    }
}
