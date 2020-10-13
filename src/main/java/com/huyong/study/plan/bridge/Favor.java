package com.huyong.study.plan.bridge;

/**
 * 描述: 实现者方
 *
 * @author huyong
 * @date 2020-10-12 7:58 下午
 */
public abstract class Favor implements Produce {
    Size size;
    public Favor(Size size) {
        this.size = size;
    }
    public abstract String getFavor();
}
