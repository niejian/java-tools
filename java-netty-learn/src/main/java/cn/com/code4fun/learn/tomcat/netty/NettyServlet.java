package cn.com.code4fun.learn.tomcat.netty;

/**
 * @className: ManualServlet
 * @desc:
 * @author: niejian
 * @time: 2022/11/6 12:59
 * @version: 0.0.1
 */
public abstract class NettyServlet {
    public void service(NettyRequest request, NettyResponse response ) throws Exception {
        if ("GET".equals(request.getMethod())) {
            doGet(request, response);
        }else {
            doPost(request, response);

        }
    }

    public abstract void doGet(NettyRequest request, NettyResponse response) throws Exception;

    public abstract void doPost(NettyRequest request, NettyResponse response) throws Exception;
}
