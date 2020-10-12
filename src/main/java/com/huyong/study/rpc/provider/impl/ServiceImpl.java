package com.huyong.study.rpc.provider.impl;

import com.huyong.study.rpc.api.IService;
import com.huyong.study.rpc.common.Service;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-09-10 9:00 下午
 */
@Service(target = IService.class)
public class ServiceImpl implements IService {
    @Override
    public void run() {
        System.out.println("i am run");
    }

    @Override
    public void say() {
        System.out.println("i am say");
    }

    @Override
    public String getName() {
        return "hello world";
    }

    @Override
    public int add(final int a, final int b) {
        return a + b;
    }
}
