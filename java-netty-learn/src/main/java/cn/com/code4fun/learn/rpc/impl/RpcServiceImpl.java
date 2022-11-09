package cn.com.code4fun.learn.rpc.impl;

import cn.com.code4fun.learn.rpc.IRpcService;

/**
 * @desc: cn.com.code4fun.learn.rpc.impl.RpcServiceImpl
 * @author: niejian9001@163.com
 * @date: 2022/11/9 11:19
 */
public class RpcServiceImpl implements IRpcService {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        return a - b;
    }

    @Override
    public int mult(int a, int b) {
        return a * b;
    }

    @Override
    public double div(int a, int b) {
        return a / b;
    }
}
