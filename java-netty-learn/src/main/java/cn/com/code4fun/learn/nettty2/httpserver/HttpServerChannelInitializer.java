package cn.com.code4fun.learn.nettty2.httpserver;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.StandardCharsets;

/**
 * @className: HttpServerChannelInitializer
 * @desc:
 * @time: 2022/10/18 14:53
 * @version: 0.0.1
 */
public class HttpServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        System.out.println("init channel....");
        socketChannel.pipeline()
                .addLast("decoder", new StringDecoder(StandardCharsets.UTF_8))
                .addLast("encoder", new StringEncoder(StandardCharsets.UTF_8))
//                .addLast("aggregator", new HttpObjectAggregator(256 * 1024))
                .addLast("handler", new HttpServerHandler());

    }
}
