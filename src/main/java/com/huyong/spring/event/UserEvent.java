package com.huyong.spring.event;

import com.huyong.spring.bean.User;

/**
 * @author huyong
 */
public class UserEvent extends Event<User> {
    public UserEvent(User user) {
        super(user);
    }
}
