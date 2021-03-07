package com.huyong.study.concrrent.forkjoin;

import java.lang.reflect.Field;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-09-15 5:18 下午
 */
public class Test {
    private String name;

    public static void main(String[] args) {
        Field[] declaredFields = Test.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            String name = declaredField.getName();
            System.out.println(name);
        }
    }
}
