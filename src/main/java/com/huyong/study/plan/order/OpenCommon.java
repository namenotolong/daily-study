package com.huyong.study.plan.order;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-08-13 10:32 下午
 */
public class OpenCommon extends BaseCommand {
    @Override
    public void execute() {
        for (final Receiver receiver : receivers) {
            System.out.println(receiver.getClass() + "开门了");
        }
    }
}
