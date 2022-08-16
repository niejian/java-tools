package cn.com.code4fun.tools.sc;

import com.netflix.loadbalancer.IRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class FeignLoadBalanceRuleSelectorAutoConfig {
    @Autowired
    private FeignLoadBalanceRule gateway2IstioLoadBalanceRule;

    @Bean
    @Primary
    public IRule getRule() {
        return gateway2IstioLoadBalanceRule;
    }
}