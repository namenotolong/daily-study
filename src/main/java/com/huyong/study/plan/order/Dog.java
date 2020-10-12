package com.huyong.study.plan.order;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-08-13 10:32 下午
 */
public class Dog implements Receiver {
    @Override
    public void action(Command command) {
        command.execute();
    }
}
