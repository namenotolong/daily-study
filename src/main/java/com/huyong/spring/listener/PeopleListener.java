package com.huyong.spring.listener;

import com.huyong.spring.bean.People;
import com.huyong.spring.event.Dog;
import com.huyong.spring.event.Event;
import com.huyong.spring.event.PeopleEvent;
import com.huyong.spring.service.PeopleService;
import com.huyong.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author huyong
 */
@Component
public class PeopleListener extends BaseListener implements ApplicationListener<PeopleEvent> {

    @Autowired
    private PeopleService iService;

    @Override
    public void onApplicationEvent(PeopleEvent event) {
        System.out.println(event);
        onService(iService);
    }
}
