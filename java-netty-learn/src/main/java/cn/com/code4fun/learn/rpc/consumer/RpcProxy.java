package cn.com.code4fun.learn.rpc.consumer;

import cn.com.code4fun.learn.rpc.InvokerProtocol;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @desc: cn.com.code4fun.learn.rpc.consumer.RpcProxy
 * @author: niejian9001@163.com
 * @date: 2022/11/9 15:51
 */
public class RpcProxy {

//    public static <T> T create(Class<?> clzz) {
//        MethodProxy methodProxy = new MethodProxy();
//    }

    private static class MethodProxy implements InvocationHandler {
        private Class<?> clazz;

        public MethodProxy(Class<?> clazz) {
            this.clazz = clazz;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (Object.class.equals(method.getDeclaringClass())) {
                try {
                    return method.invoke(this, args);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                return null;
            }
            return null;
        }


        public Object rpcInvoker(Object proxy, Method method, Object[] args) {
            //传输协议封装
            InvokerProtocol msg = new InvokerProtocol();
            msg.setClassName(this.clazz.getName());
            msg.setMethodName(method.getName());
            msg.setValues(args);
            msg.setParams(method.getParameterTypes());

            RpcProxyHandler rpcProxyHandler = new RpcProxyHandler();
            EventLoopGroup group = new NioEventLoopGroup();
            try {
                Bootstrap bootstrap = new Bootstrap();
                bootstrap.group(group)
                        .channel(NioSocketChannel.class)
                        .option(ChannelOption.TCP_NODELAY, true)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel socketChannel) throws Exception {
                                ChannelPipeline pipeline = socketChannel.pipeline();
                                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(
                                                Integer.MAX_VALUE, 0, 4, 0, 4
                                        )).addLast("frameEncoder", new LengthFieldPrepender(4))
                                        .addLast("encoder", new ObjectEncoder())
                                        .addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE,
                                                ClassResolvers.cacheDisabled(null)));
//                                        .addLast("handler", con)
                            }
                        });
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                group.shutdownGracefully();
            }

            return null;
        }
    }

}
