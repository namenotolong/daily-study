package com.huyong.study.plan.strategy;

/**
 * 描述:汽车
 *
 * @author huyong
 * @date 2020-10-13 10:06 上午
 */
public class CarStrategy implements Strategy {
    @Override
    public void action() {
        System.out.println("坐汽车");
    }
}
