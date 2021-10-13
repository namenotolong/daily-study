package com.huyong.spring.service;

import org.springframework.stereotype.Component;

@Component
public class PeopleService  implements  IService{
    @Override
    public void service() {
        System.out.println("i am people");
    }
}
