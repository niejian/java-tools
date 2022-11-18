package cn.com.code4fun.rpc.bean;

import cn.com.code4fun.rpc.config.ProviderConfig;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @className: ProviderBean
 * @desc:
 * @author: niejian
 * @time: 2022/11/18 14:38
 * @version: 0.0.1
 */
public class ProviderBean extends ProviderConfig implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 发布生产者
        doServiceExport();
    }
}
