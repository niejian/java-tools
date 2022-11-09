package cn.com.code4fun.learn.rpc;

/**
 * @desc: cn.com.code4fun.learn.rpc.IRpcService
 * @author: niejian9001@163.com
 * @date: 2022/11/9 11:05
 */
public interface IRpcService {
    public int add(int a, int b);

    int sub(int a, int b);

    int mult(int a, int b);

    double div(int a, int b);
}
