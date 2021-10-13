package com.huyong.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author huyong
 */
public abstract class Event<T> extends ApplicationEvent {
    T t;
    public Event(T t) {
        super("test");
        this.t = t;
    }

    @Override
    public String toString() {
        return "Event{" +
                "t=" + t +
                ", source=" + source +
                '}';
    }
}
