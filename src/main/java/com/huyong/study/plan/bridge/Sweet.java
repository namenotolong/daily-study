package com.huyong.study.plan.bridge;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-10-12 8:00 下午
 */
public class Sweet extends Favor {

    public Sweet(final Size size) {
        super(size);
    }

    @Override
    public String getFavor() {
        return "sweet";
    }

    @Override
    public String getProduceName() {
        return size.getSize() + ":" + getFavor();
    }
}
