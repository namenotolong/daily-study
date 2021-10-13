package com.huyong.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author huyong
 */
@Component
public class User implements Lifecycle, Comparable<Class>, UserInter {
    @Override
    public int compareTo(Class o) {
        return 0;
    }

    private Long id;
    private String name;

    private People people;

    @Autowired
    private Techer techer;

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public User() {
    }

    @PostConstruct
    public void init() {
        System.out.println("user init invoke");
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(Long id, String name,@Autowired People people) {
        this.id = id;
        this.name = name;
        this.people = people;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void start() {
        System.out.println("start user");
    }

    @Override
    public void stop() {
        System.out.println("stop user");

    }

    @Override
    public boolean isRunning() {
        System.out.println("invoke user isRunning");
        return false;
    }
}
