package com.huyong.study.plan.order.receiver;

import com.huyong.study.plan.order.command.Command;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-08-13 10:27 下午
 */
public interface Receiver {
    void action();
    void action(Command command);
}
