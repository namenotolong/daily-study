package com.huyong.study.plan.factory;

import com.huyong.study.plan.factory.money.MoneyFactory;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-10-12 3:36 下午
 */
public class FactoryProducer {
    public static AbstractFactory getMoneyFactory() {
        return new MoneyFactory();
    }
}
