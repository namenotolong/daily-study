package com.huyong.study.rpc;

import com.huyong.study.rpc.api.IService;
import com.huyong.study.rpc.concumer.RpcDelegator;
import com.huyong.study.rpc.handler.EncodeHandler;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-09-10 9:02 下午
 */
public class Test {
    public static void main(String[] args) {
        new Test().test();
    }

    public void test() {
        System.out.println(EncodeHandler.getValues());
        IService delegator = RpcDelegator.getDelegator(IService.class);
        System.out.println(delegator.getName());
        System.out.println(delegator.add(1 ,2));
    }
}
