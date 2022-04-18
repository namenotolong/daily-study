package com.huyong.study.aop.bytebuddy;

import net.bytebuddy.implementation.bind.annotation.RuntimeType;

public class RealRun {

    @RuntimeType
    public Object intercept() {
        return "hello word";
    }
}
