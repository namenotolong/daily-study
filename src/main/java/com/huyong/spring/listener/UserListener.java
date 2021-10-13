package com.huyong.spring.listener;

import com.huyong.spring.bean.People;
import com.huyong.spring.bean.User;
import com.huyong.spring.event.Event;
import com.huyong.spring.event.UserEvent;
import com.huyong.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author huyong
 */
@Component
public class UserListener  extends BaseListener implements ApplicationListener<UserEvent> {

    @Autowired
    private UserService iService;

    @Override
    public void onApplicationEvent(UserEvent event) {
        System.out.println(event);
        onService(iService);
    }
}
