package com.huyong.study.myserver.handler;

import com.huyong.study.myserver.IRequest;
import com.huyong.study.myserver.IResponse;
import com.huyong.study.myserver.core.Server;
import com.huyong.study.myserver.servlet.MyServlet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.ReferenceCountUtil;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-09-09 9:41 下午
 */
public class ServerHttpHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest req = (HttpRequest) msg;
            IRequest request = new NettyRequest(req, ctx);
            IResponse response = new NettyResponse(req, ctx);
            String uri = req.uri();
            if (Server.routes.containsKey(uri)) {
                MyServlet myServlet = Server.routes.get(uri);
                myServlet.service(request, response);
            } else {
                response.setData("404 not found");
            }
        }
        ReferenceCountUtil.release(msg);
        ctx.flush();
    }
}
