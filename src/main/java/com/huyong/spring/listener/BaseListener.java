package com.huyong.spring.listener;

import com.huyong.spring.service.IService;

/**
 * @author huyong
 */
public abstract class BaseListener  {

    void onService(IService iService) {
        System.out.println(Thread.currentThread().getName());
        iService.service();
    }
}
