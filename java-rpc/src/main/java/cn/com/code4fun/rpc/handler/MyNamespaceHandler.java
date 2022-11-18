package cn.com.code4fun.rpc.handler;

import cn.com.code4fun.rpc.config.ConsumerConfig;
import cn.com.code4fun.rpc.config.ProviderConfig;
import cn.com.code4fun.rpc.config.ServerConfig;
import cn.com.code4fun.rpc.parser.MyBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @className: MyNamespaceHandler
 * @desc:
 * @author: niejian
 * @time: 2022/11/18 14:57
 * @version: 0.0.1
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("consumer", new MyBeanDefinitionParser(ConsumerConfig.class));
        registerBeanDefinitionParser("provider", new MyBeanDefinitionParser(ProviderConfig.class));
        registerBeanDefinitionParser("server", new MyBeanDefinitionParser(ServerConfig.class));

    }
}
