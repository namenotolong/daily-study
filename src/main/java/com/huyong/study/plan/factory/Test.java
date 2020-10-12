package com.huyong.study.plan.factory;

import com.huyong.study.plan.factory.money.Money;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-10-12 3:36 下午
 */
public class Test {
    public static void main(String[] args) {
        Money america = FactoryProducer.getMoneyFactory().getMoney("america");
        System.out.println(america.getName());
    }
}
