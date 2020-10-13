package com.huyong.study.plan.strategy;

/**
 * 描述: 上下文
 *
 * @author huyong
 * @date 2020-10-13 10:07 上午
 */
public class Context {
    Strategy strategy;
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }
    public void exec() {
        strategy.action();
    }
}
