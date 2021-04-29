package com.huyong.study.test;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.*;

public class MarsGcTest {
    public ScheduledExecutorService updateLogService ;
    public MarsGcTest() {
        updateLogService = new ScheduledThreadPoolExecutor(6, new ThreadFactory() {
            private int num = 0;
            @Override
            public Thread newThread(@NotNull Runnable r) {
                Thread t = new Thread(r);
                t.setName("updateLog" + num++);
                return t;
            }
        }) {
            @Override
            protected void finalize() {
                System.out.println("我被GC了");
                super.finalize();
            }
        };
        updateLogService.scheduleAtFixedRate(() -> System.out.println("hello world"),
                0, 2, TimeUnit.SECONDS);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("对象被gc");
        super.finalize();
    }

    public static void main(String[] args) {

        MarsGcTest marsGcTest = new MarsGcTest();
        System.out.println("create");
        for (int i = 0; i < 1_000_000; i++) {
            if (i % 1_000_00 == 0)
                System.gc();
        }
        System.out.println("done");
    }
}
