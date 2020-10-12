package com.huyong.study.rpc.concumer;

import com.huyong.study.rpc.common.RequestEntity;
import com.huyong.study.rpc.handler.ConsumerHandler;
import com.huyong.study.rpc.handler.EncodeHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-09-10 10:56 下午
 */
public class RpcDelegator implements InvocationHandler {

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        Class<?>[] interfaces = proxy.getClass().getInterfaces();
        Class<?> anInterface = interfaces[0];
        Class<?> aClass = EncodeHandler.getValues().get(anInterface);
        if (aClass != null) {
            return getResponse(anInterface, method, args);
        } else {
            System.out.println("没有注册这个");
            return null;
        }
    }

    public static <T> T getDelegator(Class<T> t) {
        if (t.isInterface()) {
            return (T) Proxy.newProxyInstance(t.getClassLoader(), new Class[]{t}, new RpcDelegator());
        } else {
            return (T) Proxy.newProxyInstance(t.getClassLoader(), t.getInterfaces(), new RpcDelegator());
        }
    }

    private Object getResponse(Class<?> clazz, Method method, Object[] args) {
        RequestEntity requestEntity = new RequestEntity();
        requestEntity.setClazz(clazz);
        requestEntity.setMethod(method.getName());
        requestEntity.setParams(args);
        requestEntity.setTypes(method.getParameterTypes());
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        final ConsumerHandler consumerHandler = new ConsumerHandler();
        bootstrap.group(eventExecutors)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(final SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4))
                                .addLast(new LengthFieldPrepender(4))
                                .addLast(new ObjectEncoder())
                                .addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)))
                                .addLast(consumerHandler);
                    }
                });
        try {
            ChannelFuture localhost = bootstrap.connect("localhost", 8080).sync();
            localhost.channel().writeAndFlush(requestEntity).sync();
            localhost.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventExecutors.shutdownGracefully();
        }
        return consumerHandler.getMsg();
    }

}
