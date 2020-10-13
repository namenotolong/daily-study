package com.huyong.study.plan.bridge;

/**
 * 描述: 抽象者方实现类
 *
 * @author huyong
 * @date 2020-10-12 8:04 下午
 */
public class Big implements Size {
    @Override
    public String getSize() {
        return "big";
    }
}
