package com.huyong.study.plan.watch;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-08-13 3:54 下午
 */
public class Target implements Listener {
    private String name;

    public Target(final String name) {
        this.name = name;
    }

    @Override
    public void execute() {
        System.out.println(name + "收到消息了");
    }

    @Override
    public void execute(final Event event) {
        System.out.println();
    }

    @Override
    public String toString() {
        return "Target{" +
                "name='" + name + '\'' +
                '}';
    }
}
