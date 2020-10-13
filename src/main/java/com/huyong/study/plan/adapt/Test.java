package com.huyong.study.plan.adapt;

/**
 * 描述: 适配器模式 盗版替代 发挥一样的功能
 *
 * @author huyong
 * @date 2020-10-12 3:15 下午
 */
public class Test {
    public static void main(String[] args) {
        Phone huawei = new HuaWei();
        ChargeHead adaptor = new Adaptor(new XiaoMi());
        huawei.setChargeHead(adaptor);
        huawei.getChargeHead().charge();
    }
}
