package com.huyong.study.plan.factory.money;

import com.huyong.study.plan.factory.AbstractFactory;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-10-12 3:40 下午
 */
public class MoneyFactory extends AbstractFactory {
    @Override
    public Money getMoney(final String name) {
        if ("china".equals(name)) {
            return new PeopleMoney();
        }
        if ("america".equals(name)) {
            return new AmericaMoney();
        }
        return null;
    }
}
