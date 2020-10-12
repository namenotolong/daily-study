package com.huyong.study.plan.dress;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-08-13 9:45 下午
 */
public abstract class BaseProcess implements Process {
    Process process;
    public BaseProcess(Process process) {
        this.process = process;
    }

    @Override
    public void execute() {
        process.execute();
    }
}
