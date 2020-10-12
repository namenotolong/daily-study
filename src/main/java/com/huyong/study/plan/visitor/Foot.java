package com.huyong.study.plan.visitor;


/**
 * 描述:具体元素
 *
 * @author huyong
 * @date 2020-10-12 5:55 下午
 */
public class Foot implements Visitable {
    @Override
    public void accept(final Visitor visitor) {
        visitor.visit(this);
    }
}
