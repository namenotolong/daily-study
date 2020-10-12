package com.huyong.study.plan.adapt;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-10-12 3:16 下午
 */
public class Adaptor implements ChargeHead {
    Phone phone;
    public Adaptor(Phone phone) {
        this.phone = phone;
    }

    @Override
    public void charge() {
        new XiaoMiChargeHead().charge();
    }
}
