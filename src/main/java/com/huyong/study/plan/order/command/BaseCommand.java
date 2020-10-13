package com.huyong.study.plan.order.command;

import com.huyong.study.plan.order.receiver.Receiver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-08-13 10:28 下午
 */
public abstract class BaseCommand implements Command {
    private List<Receiver> receivers = new ArrayList<>();

    public void addReceiver(Receiver receiver) {
        receivers.add(receiver);
    }
    public void addReceiver(Receiver ... receiver) {
        receivers.addAll(Arrays.asList(receiver));
    }

    @Override
    public void execute() {
        for (final Receiver receiver : receivers) {
            receiver.action(this);
        }
    }
}
