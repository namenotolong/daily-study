package com.huyong.study.myserver.handler;

import com.huyong.study.myserver.IResponse;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.AsciiString;

import java.nio.charset.StandardCharsets;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-09-09 9:51 下午
 */
public class NettyResponse implements IResponse {
    private AsciiString contentType = HttpHeaderValues.TEXT_HTML;
    private HttpRequest request;
    private ChannelHandlerContext ctx;

    public NettyResponse(final HttpRequest request, final ChannelHandlerContext ctx) {
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
    public void setData(final String data) {
        write(data);
    }

    public void write(String out) {
        if (out == null || out.length() == 0) {
            return;
        }
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.wrappedBuffer(out.getBytes(StandardCharsets.UTF_8)));
        HttpHeaders heads = response.headers();
        heads.add(HttpHeaderNames.CONTENT_TYPE, contentType + "; charset=UTF-8");
        heads.add(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        heads.add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        ctx.write(response);
    }
}
