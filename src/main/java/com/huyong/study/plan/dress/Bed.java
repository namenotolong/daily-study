package com.huyong.study.plan.dress;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-08-13 9:47 下午
 */
public class Bed extends BaseProcess {

    public Bed(final Process process) {
        super(process);
    }

    @Override
    public void execute() {
        System.out.println("我上床了");
        super.execute();
    }
}
