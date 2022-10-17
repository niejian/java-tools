package cn.com.code4fun.learn.nettty1;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @className: MyChannelInitializer
 * @desc:
 * @time: 2022/10/17 14:59
 * @version: 0.0.1
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        System.out.println("链接开始");
        System.out.println("链接报告信息：有一客户端链接到本地服务端");
        System.out.println("链接报告IP：" + socketChannel.localAddress().getHostString());
        System.out.println("链接报告端口：" + socketChannel.localAddress().getPort());
        System.out.println("链接报告完毕。。");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端链接激活");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端离开");
        super.channelInactive(ctx);
    }
}
