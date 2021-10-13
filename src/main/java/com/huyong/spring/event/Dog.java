package com.huyong.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author huyong
 */
public class Dog extends ApplicationEvent {

    public Dog(Object source) {
        super(source);
    }
}
