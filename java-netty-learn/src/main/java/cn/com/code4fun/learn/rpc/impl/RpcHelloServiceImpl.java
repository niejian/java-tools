package cn.com.code4fun.learn.rpc.impl;

import cn.com.code4fun.learn.rpc.IRpcHelloService;

import java.time.LocalDateTime;

/**
 * @desc: cn.com.code4fun.learn.rpc.RpcHelloServiceImpl
 * @author: niejian9001@163.com
 * @date: 2022/11/9 11:14
 */
public class RpcHelloServiceImpl implements IRpcHelloService {
    @Override
    public String hello(String name) {
        String msg = String.format("hello %s, 当前时间：%s", name, LocalDateTime.now().toString());
        return msg;
    }
}
