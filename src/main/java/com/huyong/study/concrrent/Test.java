package com.huyong.study.concrrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-09-15 5:18 下午
 */
public class Test {
    static StampedLock stampedLock = new StampedLock();
    static CountDownLatch countDownLatch = new CountDownLatch(2);
    static ReentrantLock lock = new ReentrantLock();

    private static int Y = 10240;
    private static int X = 10240;
    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 1000; i++) {
            long s = stampedLock.readLock();
            System.out.println(s);
           // stampedLock.unlockWrite(s);
        }

        new Thread(() -> {
            long l = stampedLock.writeLock();
            System.out.println(l);
            System.out.println("get write lock");
            stampedLock.unlock(l);
            countDownLatch.countDown();
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long l = stampedLock.readLock();
            System.out.println(l);
            System.out.println("get read lock");
            countDownLatch.countDown();
        }).start();


        countDownLatch.await();

    }

    public void testRandom() {
        Random random = new Random();
        random.nextInt();
        ThreadLocalRandom current = ThreadLocalRandom.current();
        current.nextInt();
    }

    public static void testCache() {
        int[][] a = new int[X][Y];
        int[][] b = new int[X][Y];
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < X; i++) {
            for (int i1 = 0; i1 < Y; i1++) {
                a[i][i1] = i * 2 + i1;
            }
        }
        long start2 = System.currentTimeMillis();
        System.out.println(start2 - start1);
        long start3 = System.currentTimeMillis();
        for (int i = 0; i < X; i++) {
            for (int i1 = 0; i1 < Y; i1++) {
                b[i1][i] = i * 2 + i1;
            }
        }
        long start4 = System.currentTimeMillis();
        System.out.println(start4 - start3);
    }
}
