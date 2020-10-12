package com.huyong.study.plan.chain;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-08-13 10:09 下午
 */
public class TwoFilter implements Filter {
    @Override
    public void filter(Request request) {
        System.out.println("two");
    }
}
