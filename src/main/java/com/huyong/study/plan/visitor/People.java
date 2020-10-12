package com.huyong.study.plan.visitor;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-10-12 6:00 下午
 */
public class People implements Visitor {
    @Override
    public void visit(final Visitable item) {
        System.out.println(this.getClass().getName() + "访问了" + item.getClass());
    }
}
