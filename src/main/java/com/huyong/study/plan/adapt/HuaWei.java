package com.huyong.study.plan.adapt;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-10-12 2:54 下午
 */
public class HuaWei implements Phone {

    @Override
    public ChargeHead getChargeHead() {
        return new HuaWeiChargeHead();
    }
}
class HuaWeiChargeHead implements ChargeHead {
    @Override
    public void charge() {
        System.out.println("huawei charge");
    }
}
