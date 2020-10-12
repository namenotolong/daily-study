package com.huyong.study.plan.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-07-11 4:38 下午
 */
public class Agent implements InvocationHandler {


    public static People getPeople() {
        return (People) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{People.class}, new Agent());
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("hello world");
        System.out.println(method);
        Parameter[] parameters = method.getParameters();
        for (final Parameter parameter : parameters) {

            System.out.println(parameter);
            String name = parameter.getName();
            System.out.println(name);
        }
        method.invoke(new Target(), args);
        return null;
    }
}
