package com.huyong.spring.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author huyong
 */
public class FairZkLock extends ZookeeperLock {

    private String holderLock = null;

    {
        Stat exists = null;
        try {
            exists = getConnection().exists(lockStr, null);
            if (exists == null) {
                boolean path = createPersistentPath(lockStr);
                if (!path) {
                    throw new RuntimeException("fair lock init error");
                }
            }
        } catch (KeeperException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void lock0(String path) {
        try {
            if (path == null) {
                path = createOrderPath(lockStr);
            }
            String substring = path.substring(path.lastIndexOf('/') + 1);
            List<String> children = getConnection().getChildren(lockStr, null);
            Collections.sort(children);
            String s = children.get(0);
            if (Objects.equals(s, substring)) {
                holderLock = path;
                return;
            }
            int index = Collections.binarySearch(children, substring);
            String listen = lockStr + "/" + children.get(index - 1);
            waitLock(listen);
            lock0(path);
        } catch (KeeperException | InterruptedException e) {
            logger.error("lock happen error", e);
        }
    }

    @Override
    public void lock() {
        lock0(null);
    }

    @Override
    public void unLock() {
        if (holderLock != null) {
            boolean success = deletePath(holderLock);
            if (!success) {
                throw new RuntimeException(String.format("%s 释放锁失败", Thread.currentThread().getName()));
            }
        }
    }
}
