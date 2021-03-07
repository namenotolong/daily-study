package com.huyong.study.aop.cglib;

import com.huyong.study.aop.AopProcessImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author huyong
 */
public class Test {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Enhancer enhancer = new Enhancer();
        System.out.println(enhancer);
        enhancer.setSuperclass(AopProcessImpl.class);
        enhancer.setCallback(new CallBack());
        Object o = enhancer.create();
        ((AopProcessImpl)o).run();
        /*FastClass fastClass = FastClass.create(AopProcessImpl.class);
        Object o1 = fastClass.newInstance();
        AopProcessImpl o11 = (AopProcessImpl) o1;
        o11.run();*/
    }
}
class CallBack implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("pre");
        Object o = proxy.invokeSuper(obj, args);
        System.out.println("end");
        return o;
    }
}
