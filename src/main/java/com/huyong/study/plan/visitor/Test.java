package com.huyong.study.plan.visitor;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-10-12 5:52 下午
 */
public class Test {
    public static void main(String[] args) {
        Head head = new Head();
        Foot foot = new Foot();
        Pig pig = new Pig();
        People people = new People();
        pig.addVisitable(foot);
        pig.addVisitable(head);
        pig.show(people);
    }
}
