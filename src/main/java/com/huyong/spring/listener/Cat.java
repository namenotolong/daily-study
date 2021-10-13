package com.huyong.spring.listener;

import com.huyong.spring.event.Dog;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author huyong
 */
@Component
public class Cat implements ApplicationListener<Dog> {
    @Override
    public void onApplicationEvent(Dog event) {
        System.out.println(event);
    }
}
