package com.huyong.study.plan.visitor;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 描述:实体
 *
 * @author huyong
 * @date 2020-10-12 6:13 下午
 */
public class Pig {
    private List<Visitable> list = Lists.newArrayList();

    public void addVisitable(Visitable visitable) {
        list.add(visitable);
    }

    public void show(Visitor visitor) {
        list.forEach(visitor::visit);
    }

}
