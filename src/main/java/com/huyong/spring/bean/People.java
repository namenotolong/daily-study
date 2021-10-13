package com.huyong.spring.bean;

import org.springframework.stereotype.Component;

/**
 * @author huyong
 */
@Component
public class People {
    private Long id;
    private String name;

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
}
