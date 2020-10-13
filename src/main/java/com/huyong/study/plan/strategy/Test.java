package com.huyong.study.plan.strategy;

/**
 * 描述: 策略，屏蔽直接对具体的调用，避免维护结构复杂
 *
 * @author huyong
 * @date 2020-10-13 9:42 上午
 */
public class Test {
    public static void main(String[] args) {
        Strategy car = new CarStrategy();
        Strategy bicycle = new BicycleStrategy();
        Context context = new Context(car);
        context.exec();
        context.strategy = bicycle;
        context.exec();
    }
}
