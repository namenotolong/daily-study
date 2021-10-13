package com.huyong.spring.zk;

import org.apache.zookeeper.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author huyong
 */
public class Test {
    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        CountDownLatch countDownLatch=new CountDownLatch(1);
        ZooKeeper zooKeeper=
                new ZooKeeper("49.234.13.129:2181", 4000, new Watcher() {
                    @Override
                    public void process(WatchedEvent event) {
                        System.out.println(event);
                        if(Event.KeeperState.SyncConnected==event.getState()){
                            System.out.println("链接成功");
                            countDownLatch.countDown();
                        }
                    }
                });
        countDownLatch.await();
        //CONNECTED
        System.out.println(zooKeeper.getState());
        List<String> children = zooKeeper.getChildren("/", false);
        System.out.println(children);
        byte[] data = zooKeeper.getData("/huyong", false, null);
        System.out.println(new String(data));
        String s = zooKeeper.create("/testOrder", "hello".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(s);
    }
}
