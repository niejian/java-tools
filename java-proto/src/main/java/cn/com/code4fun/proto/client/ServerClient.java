package cn.com.code4fun.proto.client;

import cn.com.code4fun.proto.service.impl.PersonServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * 定义服务端
 * @className: ServiceClient
 * @desc:
 * @time: 2022/10/10 10:57
 * @version: 0.0.1
 */
public class ServerClient {
    private static final Integer port = 9999;
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(port)
                .addService(new PersonServiceImpl())
                .build()
                .start();

        System.out.println(String.format("grpc 服务端已启动，端口：%d", port));
        server.awaitTermination();
    }
}
