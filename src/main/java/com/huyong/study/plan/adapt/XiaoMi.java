package com.huyong.study.plan.adapt;

/**
 * 描述: 小米
 *
 * @author huyong
 * @date 2020-10-12 2:53 下午
 */
public class XiaoMi extends BasePhone {
    public XiaoMi() {
        this.chargeHead = new XiaoMiChargeHead();
    }
}
class XiaoMiChargeHead implements ChargeHead {
    @Override
    public void charge() {
        System.out.println("小米充电器正在给手机充电");
    }
}
