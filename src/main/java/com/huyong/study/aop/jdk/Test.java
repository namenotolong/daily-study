package com.huyong.study.aop.jdk;

import com.huyong.study.aop.AopProcess;
import com.huyong.study.aop.AopProcessImpl;

import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        AopProcess process = new AopProcessImpl();
        Object o = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                AopProcessImpl.class.getInterfaces(), new TestJdkTarget<>(process));
        ((AopProcess)o).run();
    }
}
