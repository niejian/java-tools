package cn.com.code4fun.proto.service.impl;

import cn.com.code4fun.proto.dto.Person;
import cn.com.code4fun.proto.service.PersonResponse;
import cn.com.code4fun.proto.service.PersonServiceGrpc;
import cn.com.code4fun.proto.service.QueryPersonByUsernameRequest;
import io.grpc.stub.StreamObserver;

/**
 * 实现生成的接口
 * @className: PersonServiceImpl
 * @desc:
 * @time: 2022/10/10 10:50
 * @version: 0.0.1
 */
public class PersonServiceImpl extends PersonServiceGrpc.PersonServiceImplBase {
    @Override
    public void getPersonByUsername(QueryPersonByUsernameRequest request, StreamObserver<PersonResponse> responseObserver) {
        // 返回结果
        PersonResponse personResponse = null;
        String username = request.getUsername();

        Person.PersonDto personDto = Person.PersonDto.newBuilder()
                .setId(111)
                .setName(username).build();
        personResponse = PersonResponse.newBuilder().setPersonDto(personDto).build();
        responseObserver.onNext(personResponse);
        responseObserver.onCompleted();
    }
}
