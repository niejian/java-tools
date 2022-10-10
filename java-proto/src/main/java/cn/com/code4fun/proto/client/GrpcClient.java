package cn.com.code4fun.proto.client;

import cn.com.code4fun.proto.service.PersonResponse;
import cn.com.code4fun.proto.service.PersonServiceGrpc;
import cn.com.code4fun.proto.service.QueryPersonByUsernameRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * 客户端启动
 * @className: GrpcClient
 * @desc:
 * @time: 2022/10/10 11:00
 * @version: 0.0.1
 */
public class GrpcClient {
    private static final String host = "localhost";
    private static final int serverPort = 9999;
    public static void main(String[] args) {
        // 获得一个通信channel
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress(host, serverPort)
                .usePlaintext()
                .build();

        try {
            // 拿到stub对象
            PersonServiceGrpc.PersonServiceBlockingStub personServiceBlockingStub = PersonServiceGrpc.newBlockingStub(managedChannel);
            QueryPersonByUsernameRequest request = QueryPersonByUsernameRequest.newBuilder().setUsername("hello").build();
            // 请求
            PersonResponse personResponse = personServiceBlockingStub.getPersonByUsername(request);
            //
            System.out.println("=========================");
            System.out.println(personResponse.toString());
            System.out.println("=========================");
        } catch (Exception e) {

            e.printStackTrace();
        }finally {
            managedChannel.shutdown();
        }
    }
}
