package com.huyong.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Techer implements UserAware  {


    @Resource
    private User user;

    @Autowired
    private People people;

    public User getUser() {
        return user;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    @Override
    @Autowired
    public void setUser(User user) {

    }

    @Override
    public String toString() {
        return "Techer{" +
                "user=" + user +
                ", people=" + people +
                '}';
    }
}
