package cn.com.code4fun.learn.rpc.handler;

import cn.com.code4fun.learn.rpc.InvokerProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @desc: cn.com.code4fun.learn.rpc.handler.RegistryHandler
 * @author: niejian9001@163.com
 * @date: 2022/11/9 11:41
 */
public class RegistryHandler extends ChannelInboundHandlerAdapter {
    // 保存所有可用服务
    public static ConcurrentHashMap<String, Object> registryMap = new ConcurrentHashMap<>(16);

    public List<String> classNameList = new ArrayList<>();


    public RegistryHandler() {
        try {
            scanClass("cn.com.code4fun.learn.rpc.impl");
            doRegistry();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doRegistry() throws Exception {
        if (null == classNameList || classNameList.size() == 0) {
            System.out.println("加载到类列表：" + classNameList.toString());
            return;
        }
        for (String className : classNameList) {
            Class<?> aClass = Class.forName(className);
            Class<?> anInterface = aClass.getInterfaces()[0];
            registryMap.put(anInterface.getName(), aClass.getDeclaredConstructor().newInstance());
        }
    }



    /**
     * 递归扫描类
     * @param packageName
     */
    public void scanClass(String packageName) {
        URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                scanClass(packageName + "." + file.getName());
            }else {
                classNameList.add(packageName + "." + file.getName().replaceAll(".class", "").trim());
            }
        }
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        InvokerProtocol request = (InvokerProtocol) msg;
        String requestClassName = request.getClassName();
        if (registryMap.contains(requestClassName)) {
            Object o = registryMap.get(requestClassName);
            if (null == o) {
                System.out.println("非法请求，className：" + requestClassName);
                return;
            }
            Method method = o.getClass().getMethod(request.getMethodName(), request.getParams());
            //调用方法
            Object result = method.invoke(o, request.getValues());
            ctx.writeAndFlush(result);
            ctx.close();

        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
