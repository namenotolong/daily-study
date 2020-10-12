package com.huyong.study.plan.proxy;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-07-11 4:40 下午
 */
public class Test {
    public static void main(String[] args) {
        People people = Agent.getPeople();
        people.run("hello");
        Method[] methods = Target.class.getMethods();
        for (final Method method : methods) {
            Parameter[] parameters = method.getParameters();
            System.out.println(Arrays.toString(parameters));
        }
    }
}
