package cn.com.code4fun.learn.tomcat.netty;

import java.time.LocalDateTime;

/**
 * @className: MyManualServlet
 * @desc:
 * @author: niejian
 * @time: 2022/11/6 13:46
 * @version: 0.0.1
 */
public class MyNettyServlet2 extends NettyServlet {
    @Override
    public void doGet(NettyRequest request, NettyResponse response) throws Exception {
        String msg = String.format("netty tomcat，second get， 当前时间：" + LocalDateTime.now());
        response.writeResp(msg);

    }

    @Override
    public void doPost(NettyRequest request, NettyResponse response) throws Exception {
        String msg = String.format("netty tomcat，second get， 当前时间：" + LocalDateTime.now());

        response.writeResp(msg);
    }
}
