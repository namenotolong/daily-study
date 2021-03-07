package com.huyong.study.aop.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author huyong
 */
public class TestJdkTarget<T> implements InvocationHandler {
    T target;
    TestJdkTarget(T t) {
        this.target = t;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("pre target");
        System.out.println(proxy.getClass().getName());
        Object invoke = method.invoke(target, args);
        System.out.println("after target");
        return invoke;
    }
}
