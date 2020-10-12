package com.huyong.study.rpc.handler;

import com.huyong.study.rpc.common.RequestEntity;
import com.huyong.study.rpc.common.Service;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-09-10 9:12 下午
 */
@ChannelHandler.Sharable
public class EncodeHandler extends ChannelInboundHandlerAdapter {

    private static final Map<Class<?>, Class<?>> values = new ConcurrentHashMap<>();

    private static Map<Class<?>, Object> instances = new ConcurrentHashMap<>();

    public static List<String> classNames = new ArrayList<>();

    private static EncodeHandler encodeHandler = new EncodeHandler();

    private EncodeHandler() {
        scannerClass("com.huyong.study.rpc");
        doRegister();
    }

    public static EncodeHandler getInstance() {
        return encodeHandler;
    }

    public static Map<Class<?>, Class<?>> getValues() {
        return values;
    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg) throws Exception {
        if (msg instanceof RequestEntity) {
            RequestEntity request = (RequestEntity) msg;
            Class<?> clazz = request.getClazz();
            Class<?> aClass = values.get(clazz);
            if (aClass != null) {
                Object instance = instances.get(aClass);
                if (instance == null) {
                    instance = aClass.newInstance();
                    instances.put(aClass, instance);
                }
                Method method = aClass.getMethod(request.getMethod(), request.getTypes());
                Object invoke = method.invoke(instance, request.getParams());
                ctx.write(invoke);
            } else {
                throw new UnsupportedOperationException("没有注册这个方法");
            }
        }
        ctx.flush();
        ctx.close();
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private void scannerClass(String packageName) {
        String s = packageName.replaceAll("\\.", File.separator);
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL resource = classLoader.getResource(s);
        assert resource != null;
        File file = new File(resource.getFile());
        File[] files = file.listFiles();
        if (files != null) {
            for (final File item : files) {
                if (item.isDirectory()) {
                    scannerClass(packageName + "." + item.getName());
                } else {
                    classNames.add(packageName + "." + item.getName().split("\\.")[0]);
                }
            }
        }
    }

    private void doRegister() {
        if (classNames.size() > 0) {
            for (final String className : classNames) {
                try {
                    Class<?> aClass = Class.forName(className);
                    Service annotation = aClass.getAnnotation(Service.class);
                    if (annotation != null) {
                        Class<?> target = annotation.target();
                        values.put(target, aClass);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
