package com.huyong.study.plan.order;

import com.huyong.study.plan.order.command.Command;

/**
 * 描述:调用类
 *
 * @author huyong
 * @date 2020-08-13 10:30 下午
 */
public class Invoker {
    Command command;
    public void call() {
        command.execute();
    }
}
