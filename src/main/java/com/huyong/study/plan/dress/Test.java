package com.huyong.study.plan.dress;

/**
 * 描述:
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
