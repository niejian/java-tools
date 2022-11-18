package cn.com.code4fun.learn.rpc;

import lombok.Data;

/**
 * 自定义协议
 * @desc: cn.com.code4fun.learn.rpc.InvokerProtocol
 * @author: niejian9001@163.com
 * @date: 2022/11/9 11:07
 */
@Data
public class InvokerProtocol {
    private String className;
    private String methodName;
    // 参数类型
    private Class<?>[] params;
    // 参数列表
    private Object[] values;
}
