package cn.com.code4fun.learn.tomcat.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;

/**
 * @className: NettyRequest
 * @desc:
 * @author: niejian
 * @time: 2022/11/6 19:33
 * @version: 0.0.1
 */
public class NettyRequest {
    private ChannelHandlerContext context;
    private HttpRequest request;
    private String url;
    private String method;

    private Map<String, List<String>> getParameters() {
        QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
        return decoder.parameters();
    }

    private String getParameter(String paramName) {
        Map<String, List<String>> parameters = getParameters();
        List<String> params = parameters.get(paramName);
        if (null == params) {
            return null;
        }
        return params.get(0);
    }

    public String getMethod() {
        return this.request.method().name();
    }

    public String getUrl() {
        return this.request.uri();
    }

    public NettyRequest(ChannelHandlerContext context, HttpRequest request) {
        this.context = context;
        this.request = request;
    }


    public ChannelHandlerContext getContext() {
        return context;
    }

    public void setContext(ChannelHandlerContext context) {
        this.context = context;
    }

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }
}
