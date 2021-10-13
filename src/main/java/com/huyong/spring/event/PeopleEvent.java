package com.huyong.spring.event;

import com.huyong.spring.bean.People;

/**
 * @author huyong
 */
public class PeopleEvent extends Event<People> {
    public PeopleEvent(People people) {
        super(people);
    }
}
