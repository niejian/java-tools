package cn.com.code4fun.tools.sc;

import com.netflix.loadbalancer.IRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class FeignLoadBalanceRuleSelectorAutoConfig {

    /**
     * https://github.com/alibaba/spring-cloud-alibaba/issues/1652
     * @return
     */
    @Bean
    @Scope("prototype")
    public IRule getRule() {
        return new FeignLoadBalanceRule();
    }
}