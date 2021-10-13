package com.huyong.spring.zk;

public class UnFairZkLock extends ZookeeperLock {

    @Override
    public void lock() {
        boolean success = createPath(lockStr);
        if (!success) {
            waitLock(lockStr);
            lock();
        }
    }

    @Override
    public void unLock() {
        boolean success = deletePath(lockStr);
        if (!success) {
            throw new RuntimeException(String.format("%s 释放锁失败", Thread.currentThread().getName()));
        }
    }
}
