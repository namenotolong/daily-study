package com.huyong.study.plan.watch;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-08-13 3:56 下午
 */
public interface Listener {
    void execute();
    void execute(Event event);
}
