package com.huyong.study.plan.adapt;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-10-12 3:15 下午
 */
public class Test {
    public static void main(String[] args) {
        Phone huawei = new HuaWei();
        new Adaptor(huawei).charge();
    }
}
