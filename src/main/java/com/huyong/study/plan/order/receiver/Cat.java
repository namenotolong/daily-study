package com.huyong.study.plan.order.receiver;

import com.huyong.study.plan.order.command.Command;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-10-13 11:37 上午
 */
public class Cat implements Receiver {
    @Override
    public void action() {
        System.out.println("🐱收到命令:" + this.getClass());
    }

    @Override
    public void action(final Command command) {
        System.out.println("🐱收到命令:" + command.getClass());
    }
}
