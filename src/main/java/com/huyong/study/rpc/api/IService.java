package com.huyong.study.rpc.api;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-09-10 8:56 下午
 */
public interface IService {
    void run();

    void say();

    String getName();


    int add(int a, int b);
}
