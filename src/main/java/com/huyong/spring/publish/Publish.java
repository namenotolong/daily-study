package com.huyong.spring.publish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author huyong
 */
@Component
public class Publish {

    @Autowired
    private ApplicationEventPublisher publisher;

    public void publish(ApplicationEvent event) {
        publisher.publishEvent(event);
    }

}
