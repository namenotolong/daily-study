package com.huyong.study.plan.bridge;

/**
 * 描述:桥接模式，两个实体组合，针对组合的产品，一方看做抽象，一方看做实现
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
