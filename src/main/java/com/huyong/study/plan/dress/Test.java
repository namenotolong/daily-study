package com.huyong.study.plan.dress;

/**
 * 描述: 装饰着模式，动态的增加功能
 *
 * @author huyong
 * @date 2020-08-13 9:50 下午
 */
public class Test {
    public static void main(String[] args) {
        Process target = new Target();
        new Door(new Bed(target)).execute();
    }
}
