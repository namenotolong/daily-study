package com.huyong.spring.zk;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * @author huyong
 */
public abstract class ZookeeperLock implements DistributeLock {

    protected static Logger logger = LoggerFactory.getLogger(ZookeeperLock.class);

    protected String url = "49.234.13.129:2181";

    protected String lockStr = "/hello";
    protected String child = "/";

    static ZooKeeper zooKeeper = null;

    static {

        CountDownLatch countDownLatch=new CountDownLatch(1);
        try {
            zooKeeper=
                    new ZooKeeper("49.234.13.129:2181", 4000, new Watcher() {
                        @Override
                        public void process(WatchedEvent event) {
                            System.out.println(event);
                            if(Event.KeeperState.SyncConnected==event.getState()){
                                countDownLatch.countDown();
                            }
                        }
                    });
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected ZooKeeper getConnection() {
        return zooKeeper;
    }

    protected boolean createPath(String path) {
        try {
            getConnection().create(path, lockStr.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            return true;
        } catch (KeeperException | InterruptedException e) {
            logger.error("创建path失败 :{}", Thread.currentThread().getName());
        }
        return false;
    }

    protected boolean createPersistentPath(String path) {
        try {
            getConnection().create(path, lockStr.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            return true;
        } catch (KeeperException | InterruptedException e) {
            logger.error("创建path失败 :{}", Thread.currentThread().getName());
        }
        return false;
    }

    protected String createOrderPath(String path) {
        try {
            return getConnection().create(lockStr + child, lockStr.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        } catch (KeeperException | InterruptedException e) {
            logger.error("创建path失败 :{}", Thread.currentThread().getName(), e);
            throw new RuntimeException(String.format("create order path %s error", path));
        }
    }

    protected boolean deletePath(String path) {
        try {
            getConnection().delete(path, -1);
            return true;
        } catch (InterruptedException e) {
            logger.error("remote path not valid path:{} {}", path, Thread.currentThread().getName());
        } catch (KeeperException e) {
            logger.error("zookeeper happen error {} {}", path, e);
        }
        return false;
    }

    protected void waitLock(String path)  {
        try {
            CountDownLatch countDownLatch=new CountDownLatch(1);
            zooKeeper.exists(path, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    boolean delete = event.getType() == Event.EventType.NodeDeleted;
                    if (delete) {
                        countDownLatch.countDown();
                    }
                }
            });
            countDownLatch.await();
            return;
        } catch (KeeperException e) {
            logger.error("zookeeper happen transaction error {} {}", path, e);
        } catch (InterruptedException e) {
            logger.error("wait lock not valid path:{} {}", path, Thread.currentThread().getName());
        }
        throw new RuntimeException("wait lock error");
    }
}
