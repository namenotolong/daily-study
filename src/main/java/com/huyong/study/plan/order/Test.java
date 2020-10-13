package com.huyong.study.plan.order;

import com.huyong.study.plan.order.command.BaseCommand;
import com.huyong.study.plan.order.command.CloseCommand;
import com.huyong.study.plan.order.command.OpenCommon;
import com.huyong.study.plan.order.receiver.Cat;
import com.huyong.study.plan.order.receiver.Dog;
import com.huyong.study.plan.order.receiver.Receiver;

/**
 * 描述: 命令模式，针对多种执行行为，便于切换，只用执行类调用，避免直接调用行为方法，降低耦合
 *
 * @author huyong
 * @date 2020-08-13 10:27 下午
 */
public class Test {
    public static void main(String[] args) {
        BaseCommand command = new OpenCommon();
        BaseCommand closeCommand = new CloseCommand();
        Receiver dog = new Dog();
        Receiver cat = new Cat();
        command.addReceiver(dog, cat);
        Invoker invoker = new Invoker();
        invoker.command = command;
        invoker.call();
        closeCommand.addReceiver(dog);
        invoker.command = closeCommand;
        invoker.call();
    }
}
