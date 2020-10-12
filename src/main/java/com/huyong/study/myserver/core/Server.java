package com.huyong.study.myserver.core;

import com.huyong.study.myserver.Request;
import com.huyong.study.myserver.Response;
import com.huyong.study.myserver.handler.ServerHttpHandler;
import com.huyong.study.myserver.servlet.MyServlet;
import com.huyong.study.netty.HttpHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-08-19 10:09 下午
 */
public class Server {
    public static Map<String, MyServlet> routes = new HashMap<String, MyServlet>() {
        @Override
        public boolean containsKey(final Object key) {
            if (super.containsKey(key)) {
                return true;
            }
            if (null == key) {
                return false;
            }
            for (final String s : routes.keySet()) {
                if (String.valueOf(key).startsWith(s)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public MyServlet get(final Object key) {
            MyServlet myServlet = super.get(key);
            if (myServlet != null) {
                return myServlet;
            }
            if (key == null) {
                return null;
            }
            for (final String s : routes.keySet()) {
                if (String.valueOf(key).startsWith(s)) {
                    return super.get(s);
                }
            }
            return null;
        }
    };
    static class MyThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        MyThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(null, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
        }
    }


    private static final ThreadPoolExecutor threadPool =
            new ThreadPoolExecutor(20, 20,
                    10L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingDeque<>(), r -> {
                        Thread thread = new Thread(r);
                        thread.setName("new task");
                        return thread;
                    });

    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Server server = new Server();
        server.initServlets();
        server.startNettyServer();
    }

    public void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            Socket accept = serverSocket.accept();
            threadPool.execute(() -> {
                try {
                    Request request = new Request(accept.getInputStream());
                    Response response = new Response(accept.getOutputStream());
                    process(request, response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void startNettyServer() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        serverBootstrap.group(eventExecutors)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(final SocketChannel socketChannel) {
                        socketChannel.pipeline()
                                .addLast(new HttpRequestDecoder())
                                .addLast(new HttpResponseEncoder())
                                .addLast(new HttpObjectAggregator(512 * 1024))
                                .addLast(new ServerHttpHandler());
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE);
        try {
            ChannelFuture sync = serverBootstrap.bind(8080).sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventExecutors.shutdownGracefully();
        }
    }

    public void process(Request request, Response response) {
        MyServlet myServlet = routes.get(request.getUrl());
        if (myServlet == null) {
            response.setData("没有这个地址");
        } else {
            myServlet.service(request, response);
        }
        response.flush();
        try {
            response.getOutputStream().flush();
            response.getOutputStream().close();
            request.getInputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void initServlets() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        List<String> list = Files.readAllLines(Paths.get("./config.properties"));
        List<String> urls = new ArrayList<>();
        List<MyServlet> servlets = new ArrayList<>();
        for (String item : list) {
            String value = item.split("=")[1];
            if (item.startsWith("servlet.name")) {
                urls.add(value);
            } else {
                Class<?> aClass = ClassLoader.getSystemClassLoader().loadClass(value);
                Object servlet = aClass.newInstance();
                if (servlet instanceof MyServlet) {
                    servlets.add((MyServlet) servlet);
                } else {
                    throw new UnsupportedOperationException("没有实现MyServlet");
                }
            }
        }
        for (int i = 0; i < urls.size(); i++) {
            try {
                routes.put(urls.get(i), servlets.get(i));
            } catch (Exception e) {
                throw new UnsupportedOperationException("配置不均匀");
            }
        }
    }
}
