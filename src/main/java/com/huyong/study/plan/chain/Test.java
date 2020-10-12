package com.huyong.study.plan.chain;

/**
 * 描述:
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
