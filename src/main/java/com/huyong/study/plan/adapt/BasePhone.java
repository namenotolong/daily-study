package com.huyong.study.plan.adapt;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-10-13 4:16 下午
 */
public class BasePhone implements Phone {

    protected ChargeHead chargeHead;

    public BasePhone() {
    }

    @Override
    public ChargeHead getChargeHead() {
        return chargeHead;
    }

    @Override
    public void setChargeHead(final ChargeHead chargeHead) {
        this.chargeHead = chargeHead;
    }
}
