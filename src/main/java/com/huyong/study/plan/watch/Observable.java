package com.huyong.study.plan.watch;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-08-13 3:54 下午
 */
public interface Observable {
    void addListener(Listener... listener);
    void removeListener(Listener listener);
    void notifyListener();
}
