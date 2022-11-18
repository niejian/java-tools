package cn.com.code4fun.rpc.bean;

import cn.com.code4fun.rpc.config.ConsumerConfig;
import org.springframework.beans.factory.FactoryBean;

/**
 * @className: ConsumerBean
 * @desc:
 * @author: niejian
 * @time: 2022/11/18 15:11
 * @version: 0.0.1
 */
public class ConsumerBean<T> extends ConsumerConfig implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return refer();
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
