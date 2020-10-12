package com.huyong.study.plan.order;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-08-13 10:28 下午
 */
public abstract class BaseCommand implements Command {
    List<Receiver> receivers = new ArrayList<>();

    void addReceiver(Receiver receiver) {
        receivers.add(receiver);
    }
}
