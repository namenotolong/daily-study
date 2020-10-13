package com.huyong.study.plan.adapt;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-10-12 2:54 下午
 */
public class HuaWei extends BasePhone {
    public HuaWei() {
        this.chargeHead = new HuaWeiChargeHead();
    }
}
class HuaWeiChargeHead implements ChargeHead {
    @Override
    public void charge() {
        System.out.println("华为充电器正在给手机充电");
    }
}
