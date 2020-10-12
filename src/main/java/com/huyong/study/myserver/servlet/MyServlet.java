package com.huyong.study.myserver.servlet;

import com.huyong.study.myserver.IRequest;
import com.huyong.study.myserver.IResponse;
import com.huyong.study.myserver.Request;
import com.huyong.study.myserver.Response;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-08-19 9:53 下午
 */
public interface MyServlet {

    /**
     * 处理中心
     */
    void service(IRequest request, IResponse response);

    /**
     * get请求
     */
    void get(IRequest request, IResponse response);

    /**
     * Post请求
     */
    void post(IRequest request, IResponse response);
}
