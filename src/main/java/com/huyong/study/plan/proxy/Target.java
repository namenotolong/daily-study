package com.huyong.study.plan.proxy;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-07-11 4:42 下午
 */
public class Target implements People {
    @Override
    public void say() {
        System.out.println("say");
    }

    @Override
    public void run(String name) {
        System.out.println("run");
    }

    @Override
    public void test() {
        System.out.println("test");
    }
}
