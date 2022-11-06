package cn.com.code4fun.learn.tomcat.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @className: NettyTomcat
 * @desc:
 * @author: niejian
 * @time: 2022/11/6 19:43
 * @version: 0.0.1
 */
public class NettyTomcat {
    private int port = 8088;
    private Map<String, NettyServlet> servletMapping = new HashMap<>(16);
    private Properties webXml = new Properties();

    private void init() {
        try {
            String path = this.getClass().getResource("/").getPath();
            FileInputStream inputStream = new FileInputStream(path + "web-netty.properties");
            webXml.load(inputStream);

            Set<Object> keySet = webXml.keySet();

            for (Object key : keySet) {
                String k = (String) key;
                if (k.endsWith(".url")) {
                    String servletName = k.replaceAll("\\.url$", "");
                    String url = webXml.getProperty(k);
                    String className = webXml.getProperty(servletName + ".className");
                    NettyServlet servlet = (NettyServlet) Class.forName(className).newInstance();
                    servletMapping.put(url, servlet);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        init();

        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel client) throws Exception {
                            client.pipeline()
                                    .addLast(new HttpResponseEncoder())
                                    .addLast(new HttpRequestDecoder())
                                    .addLast(new NettyTomcatHandler());

                        }
                    })
                    // 针对主线程配置，分配线程最大数量128
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture sync = serverBootstrap.bind(port).sync();
            System.out.println("基于netty的http服务器已启动，端口：" + port);
            sync.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }


    class NettyTomcatHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if (msg instanceof HttpRequest) {
                System.out.println("hello");
                HttpRequest request = (HttpRequest) msg;
                // 转交给自己实现的request处理
                NettyRequest nettyRequest = new NettyRequest(ctx, request);
                NettyResponse nettyResponse = new NettyResponse(ctx, request);
                // 处理实际业务
                String url = nettyRequest.getUrl();
                if (servletMapping.containsKey(url)) {
                    servletMapping.get(url).service(nettyRequest, nettyResponse);
                }else {
                    nettyResponse.writeResp("404");
                }

            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
        }
    }
}
