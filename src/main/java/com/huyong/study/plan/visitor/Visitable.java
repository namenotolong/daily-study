package com.huyong.study.plan.visitor;

/**
 * 描述:抽象实体元素元素
 *
 * @author huyong
 * @date 2020-10-12 6:05 下午
 */
public interface Visitable {
    void accept(Visitor visitor);
}
