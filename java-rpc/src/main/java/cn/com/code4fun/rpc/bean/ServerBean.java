package cn.com.code4fun.rpc.bean;

import cn.com.code4fun.rpc.config.ServerConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @className: ServerBean
 * @desc:
 * @author: niejian
 * @time: 2022/11/18 15:12
 * @version: 0.0.1
 */
public class ServerBean extends ServerConfig implements InitializingBean, ApplicationContextAware {
    private transient ApplicationContext applicationContext;
    @Override
    public void afterPropertiesSet() throws Exception {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.format("服务端信息 => 注册中心地址：%s, 端口：%d ", getHost(), getPort());
    }
}
