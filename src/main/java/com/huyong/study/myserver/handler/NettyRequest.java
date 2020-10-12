package com.huyong.study.myserver.handler;

import com.huyong.study.myserver.IRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-09-09 9:51 下午
 */
public class NettyRequest implements IRequest {
    private HttpRequest request;
    private ChannelHandlerContext ctx;

    public NettyRequest(final HttpRequest request, final ChannelHandlerContext ctx) {
        this.request = request;
        this.ctx = ctx;
    }

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(final HttpRequest request) {
        this.request = request;
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public void setCtx(final ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public Map<String, String> getParams() {
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(request.uri());
        Map<String, String> result = new HashMap<>();
        queryStringDecoder.parameters().forEach((k, v) -> {
            if (v != null) {
                result.put(k, v.get(0));
            }
        });
        return result;
    }

    @Override
    public String getParam(final String name) {
        return getParams().get(name);
    }
}
