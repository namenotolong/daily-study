package com.huyong.study.zookeeper;


import com.google.common.collect.Lists;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-09-17 9:35 上午
 */
public class Test {

    private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors() * 2 + 1);

    public static void test() {
        executorService.scheduleAtFixedRate((() -> {
            //遍历集合，可以下载的就下载
        }), 0, 60, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = new ZooKeeper("119.23.221.63:2181", 1000, new Watcher() {
            @Override
            public void process(final WatchedEvent watchedEvent) {
                System.out.println(watchedEvent);
            }
        });
        zooKeeper.create("/huyongtest1", "hello world".getBytes(),
                ZooDefs.Ids.READ_ACL_UNSAFE, CreateMode.PERSISTENT);
        byte[] data = zooKeeper.getData("/huyong", new Watcher() {
            @Override
            public void process(final WatchedEvent watchedEvent) {
                System.out.println(watchedEvent);
            }
        }, null);
        zooKeeper.addWatch("/huyong", watchedEvent -> {
            System.out.println(watchedEvent.getPath());
            System.out.println(watchedEvent.getState());
            System.out.println(watchedEvent.getType());
            System.out.println(watchedEvent.getWrapper());
        }, AddWatchMode.PERSISTENT_RECURSIVE);
        System.out.println(new String(data));
        Thread.sleep(100000);
    }
    public static void testClient() {
    }
}
