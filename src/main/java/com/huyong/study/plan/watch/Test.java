package com.huyong.study.plan.watch;

/**
 * 描述: 观察者模式，感应者（发出事件）、监听者（处理事件）、事件
 * 处理多事件、多行为、多监听者场景，可扩展事件类、监听者处理多种事件类型
 *
 * @author huyong
 * @date 2020-08-13 4:00 下午
 */
public class Test {
    public static void main(String[] args) {
        Observable observable = new BossObservable();
        Listener a = new Target("小明");
        Listener b = new Target("小红");
        Listener c = new Target("小张");
        Listener d = new Target("小c");
        Listener e = new Target("小第");
        observable.addListener(a,b,c,d,e);
        observable.notifyListener();
    }
}
