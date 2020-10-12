package com.huyong.study.plan.order;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-08-13 10:27 下午
 */
public class Test {
    public static void main(String[] args) {
        BaseCommand command = new OpenCommon();
        Receiver receiver = new Dog();
        command.addReceiver(receiver);
        command.execute();
    }
}
