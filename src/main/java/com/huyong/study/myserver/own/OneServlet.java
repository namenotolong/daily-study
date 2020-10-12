package com.huyong.study.myserver.own;

import com.huyong.study.myserver.IRequest;
import com.huyong.study.myserver.IResponse;
import com.huyong.study.myserver.Request;
import com.huyong.study.myserver.Response;
import com.huyong.study.myserver.servlet.MyServlet;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-08-19 10:02 下午
 */
public class OneServlet implements MyServlet {
    @Override
    public void service(final IRequest request, final IResponse response) {
        String name = request.getParams().get("name");
        StringBuilder sb = new StringBuilder();
        sb.append("<h1>").append(name).append("</h1>");
        sb.append("<span>欢迎您的到来").append("</span>");
        response.setData(sb.toString());
    }

    @Override
    public void get(final IRequest request, final IResponse response) {

    }

    @Override
    public void post(final IRequest request, final IResponse response) {

    }
}
