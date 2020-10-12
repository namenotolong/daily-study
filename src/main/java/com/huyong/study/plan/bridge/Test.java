package com.huyong.study.plan.bridge;

/**
 * 描述:桥接模式
 *
 * @author huyong
 * @date 2020-10-12 7:56 下午
 */
public class Test {
    public static void main(String[] args) {
        Size big = new Big();
        Favor sweet = new Sweet(big);
        System.out.println(sweet.getProduceName());
    }
}
