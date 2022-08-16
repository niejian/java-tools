package cn.com.code4fun.tools.sc;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FeignLoadBalanceRule extends ClientConfigEnabledRoundRobinRule implements EnvironmentAware {
    private Logger logger = LoggerFactory.getLogger(FeignLoadBalanceRule.class);
    private LoadBalancerStats loadBalancerStats;
    private Environment environment;
    private static final String INVALID_PROVIDERS_IP_PREFIX_KEY = "spring.cloud.provider.invalid.ips";



    @Override
    public void setLoadBalancer(ILoadBalancer lb) {
        super.setLoadBalancer(lb);
        if (lb instanceof AbstractLoadBalancer) {
            loadBalancerStats = ((AbstractLoadBalancer) lb).getLoadBalancerStats();
        }
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object key) {
        if (loadBalancerStats == null) {
            return super.choose(key);
        }
        List<Server> serverList = getLoadBalancer().getAllServers();
        String invalidIp = environment.getProperty(INVALID_PROVIDERS_IP_PREFIX_KEY);
        if (null != invalidIp) {
            String[] invalidIps = invalidIp.split(",");
            List<String> invalidIpList = Stream.of(invalidIps).collect(Collectors.toList());
            logger.info("spring cloud invalid provider ip prefix, {}", invalidIp);
            List<Server> servers = new ArrayList<>();
            // 判断当前服务是否需要过滤
            for (Server s : serverList){
                String host = s.getHost();
                boolean validIp = true;
                for (String ip : invalidIpList) {
                    if (host.startsWith(ip)) {
                        logger.info("exclude invalid provider: {} ip: {}", s.getMetaInfo().getAppName(), ip);
                        validIp = false;
                        break;
                    }
                }

                if (validIp) {
                    servers.add(s);
                }
            }
            serverList = servers;
        }

        logger.info("avaliable provider services: {}", serverList.toArray());

        int minimalConcurrentConnections = Integer.MAX_VALUE;
        long currentTime = System.currentTimeMillis();
        Server chosen = null;
        for (Server server : serverList) {
            ServerStats serverStats = loadBalancerStats.getSingleServerStat(server);
            if (!serverStats.isCircuitBreakerTripped(currentTime)) {
                int concurrentConnections = serverStats.getActiveRequestsCount(currentTime);
                if (concurrentConnections < minimalConcurrentConnections) {
                    minimalConcurrentConnections = concurrentConnections;
                    chosen = server;
                }
            }
        }
        if (chosen == null) {
            return super.choose(key);
        } else {
            return chosen;
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}