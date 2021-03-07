package com.huyong.study.plan.visitor;

/**
 * 描述: 抽象观察者、具体观察者、实体（含有的元素）、抽象实体元素、具体实体元素
 *
 * @author huyong
 * @date 2020-10-12 5:52 下午
 */
public class Test {
    public static void main(String[] args) {
        Visitable head = new Head();
        Visitable foot = new Foot();
        Pig pig = new Pig();
        Visitor people = new People();
        pig.addVisitable(foot);
        pig.addVisitable(head);
        pig.show(people);
    }
}
