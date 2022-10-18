package cn.com.code4fun.learn.nettty2.httpserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.util.AsciiString;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @className: HttpServerHandler
 * @desc:
 * @time: 2022/10/18 14:39
 * @version: 0.0.1
 */
public class HttpServerHandler extends ChannelInboundHandlerAdapter {
    protected AsciiString contentType = HttpHeaderValues.TEXT_PLAIN;


    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        System.out.println("获取request信息\n\r");
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] msgByte = new byte[buf.readableBytes()];
//        buf.readBytes(msgByte);
        System.out.print(new Date() + "接收到消息：" + msg);
//        System.out.println(new String(msgByte, Charset.forName("UTF-8")));

        String resp = "收到消息；当前时间：" + LocalDateTime.now();

        String str = resp + "\r\n";
        ByteBuf buf = Unpooled.buffer(str.getBytes().length);
        buf.writeBytes(str.getBytes("gbk"));
        channelHandlerContext.writeAndFlush(buf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("===========>exceptionCaught");
        if (null != cause) {
            super.exceptionCaught(ctx, cause);
        }
        if (null != ctx) {
            ctx.close();
        }
    }
}
