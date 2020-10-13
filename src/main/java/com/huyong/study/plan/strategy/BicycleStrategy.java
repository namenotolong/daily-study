package com.huyong.study.plan.strategy;

/**
 * 描述: 自行车
 *
 * @author huyong
 * @date 2020-10-13 10:07 上午
 */
public class BicycleStrategy implements Strategy {
    @Override
    public void action() {
        System.out.println("自行车");
    }
}
