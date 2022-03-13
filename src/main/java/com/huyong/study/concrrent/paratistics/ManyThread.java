package com.huyong.study.concrrent.paratistics;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author huyong
 */
public class ManyThread {

    static int curValue = 1;
    static int max = 3;
    static int min = 1;

    static boolean allReadDy = false;
    static volatile int count = 0;

    static ReentrantLock reentrantLock = new ReentrantLock(true);
    static Condition condition;
    static {
        condition = reentrantLock.newCondition();
    }

    static class PrintValueThread implements Runnable{
        int value;
        public PrintValueThread(int value) {
            this.value = value;
        }
        @Override
        public void run() {
            while (true) {
                if (value == curValue) {
                    synchronized (ManyThread.class) {
                        System.out.println(value);
                        if (curValue < 3) {
                            ++curValue;
                        } else {
                            curValue = min;
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static Object lock = new Object();
    static class Produce implements Runnable {
        @Override
        public void run() {
            while (true) {
                /*synchronized (lock) {
                    System.out.println("我是生产者");
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }*/
                reentrantLock.lock();
                try {
                    System.out.println("i am produce");
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                /*synchronized (lock) {
                    System.out.println("我是消费者");
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }*/
                reentrantLock.lock();
                try {
                    System.out.println("i am consumer");
                    Thread.sleep(00);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }
        }
    }

    /**
     * 三个线程交替打印1，2，3
     */
    public void threadThreadPrint() {
        Thread one = new Thread(new PrintValueThread(1));
        Thread two = new Thread(new PrintValueThread(2));
        Thread thread = new Thread(new PrintValueThread(3));

        Queue<Thread> queue = new LinkedBlockingQueue<>();
        queue.offer(one);
        queue.offer(two);
        queue.offer(thread);

        one.start();
        two.start();
        thread.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException ignored) {
        }

    }

    public void produceAndConsumer() {
        Thread produce = new Thread(new Produce());
        Thread consumer = new Thread(new Consumer());
        produce.start();;
        consumer.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ManyThread().produceAndConsumer();
    }
}

