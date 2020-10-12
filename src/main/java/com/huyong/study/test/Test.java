package com.huyong.study.test;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-07-14 9:06 下午
 */
public class Test {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(10);
        final AtomicInteger count = new AtomicInteger();
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println(count.getAndIncrement());
            if (count.get() > 10) {
                throw new RuntimeException();
            }
        }, 0 , 1, TimeUnit.SECONDS);
    }
}
