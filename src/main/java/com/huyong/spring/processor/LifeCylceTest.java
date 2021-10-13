package com.huyong.spring.processor;

import org.springframework.context.SmartLifecycle;

public class LifeCylceTest implements SmartLifecycle {
    @Override
    public void start() {
        System.out.println("LifeCylceTest start invoke");
    }

    @Override
    public void stop() {
        System.out.println("LifeCylceTest stop invoke");
    }

    @Override
    public boolean isRunning() {
        System.out.println("LifeCylceTest isRunning invoke");
        return false;
    }
}
