package com.huyong.spring.zk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huyong
 */
public class LockTest {
    private static Logger logger = LoggerFactory.getLogger(ZookeeperLock.class);

    public static void main(String[] args) throws InterruptedException {
        DistributeLock lock = new FairZkLock();
    }
}
