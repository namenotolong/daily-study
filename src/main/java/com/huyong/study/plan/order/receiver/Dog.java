package com.huyong.study.plan.order.receiver;

import com.huyong.study.plan.order.command.Command;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-08-13 10:32 下午
 */
public class Dog implements Receiver {
    @Override
    public void action() {
        System.out.println("狗接受命令");
    }

    @Override
    public void action(Command command) {
        System.out.println("狗收到命令:" + command.getClass());
    }
}
