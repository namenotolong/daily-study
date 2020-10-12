package com.huyong.study.plan.adapt;

/**
 * 描述: 小米
 *
 * @author huyong
 * @date 2020-10-12 2:53 下午
 */
public class XiaoMi implements Phone {
    @Override
    public ChargeHead getChargeHead() {
        return new XiaoMiChargeHead();
    }
}
class XiaoMiChargeHead implements ChargeHead {
    @Override
    public void charge() {
        System.out.println("xiaomi charge");
    }
}
