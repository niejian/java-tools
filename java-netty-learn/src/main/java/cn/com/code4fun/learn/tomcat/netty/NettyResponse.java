package cn.com.code4fun.learn.tomcat.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

import java.nio.charset.StandardCharsets;

/**
 * @className: NettyResponse
 * @desc:
 * @author: niejian
 * @time: 2022/11/6 19:38
 * @version: 0.0.1
 */
public class NettyResponse {
    private ChannelHandlerContext context;
    private HttpRequest request;

    public void writeResp(String out) {
        if (null == out || "".equals(out)) {
            System.out.println("无响应信息");
            return;
        }
        // 设置http请求头信息
        FullHttpResponse httpResponse = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.wrappedBuffer(out.getBytes(StandardCharsets.UTF_8)));
        httpResponse.headers().set("Content-Type", "text/html");
        context.writeAndFlush(httpResponse);
        context.close();

    }

    public ChannelHandlerContext getContext() {
        return context;
    }

    public void setContext(ChannelHandlerContext context) {
        this.context = context;
    }

    public HttpRequest getResponse() {
        return request;
    }

    public void setResponse(HttpRequest response) {
        this.request = response;
    }

    public NettyResponse(ChannelHandlerContext context, HttpRequest response) {
        this.context = context;
        this.request = response;
    }
}
