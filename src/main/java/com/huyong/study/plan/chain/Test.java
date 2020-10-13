package com.huyong.study.plan.chain;

/**
 * 描述: 责任链模式，流水线的执行模式，便于增加、减少流程
 *
 * @author huyong
 * @date 2020-08-13 10:06 下午
 */
public class Test {
    public static void main(String[] args) {
        Request request = new Request();
        Chain chain = new Chain().addFilter(new OneFilter()).addFilter(new TwoFilter());
        chain.doChain(request);
    }
}
