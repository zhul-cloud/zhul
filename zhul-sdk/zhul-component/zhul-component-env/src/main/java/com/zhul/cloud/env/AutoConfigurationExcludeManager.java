package com.zhul.cloud.env;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yanglikai on 2021/1/13.
 */
public final class AutoConfigurationExcludeManager {

  private static final Set<String> excludes = new HashSet<>();

  public static Set<String> excludeToStream() {
    excludes.add(
        "com.alibaba.cloud.stream.binder.rocketmq.config.RocketMQComponent4BinderAutoConfiguration");
    excludes.add("org.apache.rocketmq.spring.autoconfigure.RocketMQAutoConfiguration");

    excludes.add("org.springframework.cloud.stream.config.ChannelBindingAutoConfiguration");
    excludes.add("org.springframework.cloud.stream.config.BindersHealthIndicatorAutoConfiguration");
    excludes.add("org.springframework.cloud.stream.config.ChannelsEndpointAutoConfiguration");
    excludes.add("org.springframework.cloud.stream.config.BindingsEndpointAutoConfiguration");
    excludes.add("org.springframework.cloud.stream.config.BindingServiceConfiguration");
    excludes.add("org.springframework.cloud.stream.function.FunctionConfiguration");

    return excludes;
  }

  public static Set<String> excludeToCache() {
    excludes.add("com.zhul.cloud.cache.spring.boot.ZhulCacheAutoConfiguration");

    excludes.add("org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration");
    excludes
        .add("org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration");
    excludes.add(
        "org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration");
    excludes.add("net.oschina.j2cache.autoconfigure.J2CacheAutoConfiguration");
    excludes.add("net.oschina.j2cache.autoconfigure.J2CacheSpringCacheAutoConfiguration");
    excludes.add("net.oschina.j2cache.autoconfigure.J2CacheSpringRedisAutoConfiguration");

    return excludes;
  }

  public static Set<String> excludeToLock() {
    excludes.add("com.zhul.cloud.lock.spring.boot.ZhulLockAutoConfiguration");

    excludes.add("org.redisson.spring.starter.RedissonAutoConfiguration");

    return excludes;
  }

  public static Set<String> excludeToTransaction() {
    excludes.add("com.alibaba.cloud.seata.rest.SeataRestTemplateAutoConfiguration");
    excludes.add("com.alibaba.cloud.seata.web.SeataHandlerInterceptorConfiguration");
    excludes.add("com.alibaba.cloud.seata.feign.SeataFeignClientAutoConfiguration");
    excludes.add("com.alibaba.cloud.seata.feign.hystrix.SeataHystrixAutoConfiguration");

    excludes.add("io.seata.spring.boot.autoconfigure.SeataAutoConfiguration");

    return excludes;
  }

  public static Set<String> excludeToProtector() {
    excludes.add("com.alibaba.cloud.sentinel.SentinelWebAutoConfiguration");
    excludes.add("com.alibaba.cloud.sentinel.SentinelWebFluxAutoConfiguration");
    excludes.add("com.alibaba.cloud.sentinel.endpoint.SentinelEndpointAutoConfiguration");
    excludes.add("com.alibaba.cloud.sentinel.custom.SentinelAutoConfiguration");
    excludes.add("com.alibaba.cloud.sentinel.feign.SentinelFeignAutoConfiguration");

    excludes
        .add("com.alibaba.cloud.circuitbreaker.sentinel.SentinelCircuitBreakerAutoConfiguration");
    excludes.add(
        "com.alibaba.cloud.circuitbreaker.sentinel.ReactiveSentinelCircuitBreakerAutoConfiguration");

    return excludes;
  }

  public static Set<String> excludeToDatabase() {
    excludes
        .add("org.apache.shardingsphere.sharding.spring.boot.ShardingRuleSpringBootConfiguration");
    excludes.add("org.apache.shardingsphere.shadow.spring.boot.ShadowRuleSpringBootConfiguration");
    excludes.add(
        "org.apache.shardingsphere.replicaquery.spring.boot.ReplicaQueryRuleSpringbootConfiguration");
    excludes.add("org.apache.shardingsphere.spring.boot.SpringBootConfiguration");
    excludes
        .add("org.apache.shardingsphere.encrypt.spring.boot.EncryptRuleSpringBootConfiguration");

    excludes
        .add("com.baomidou.mybatisplus.autoconfigure.MybatisPlusLanguageDriverAutoConfiguration");
    excludes.add("com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration");

    excludes.add("org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration");

    return excludes;
  }

  public static Set<String> excludeToConfig() {
    excludes.add("com.alibaba.cloud.nacos.discovery.NacosDiscoveryAutoConfiguration");
    excludes.add("com.alibaba.cloud.nacos.ribbon.RibbonNacosAutoConfiguration");
    excludes.add("com.alibaba.cloud.nacos.endpoint.NacosDiscoveryEndpointAutoConfiguration");
    excludes.add("com.alibaba.cloud.nacos.registry.NacosServiceRegistryAutoConfiguration");
    excludes.add("com.alibaba.cloud.nacos.discovery.NacosDiscoveryClientConfiguration");
    excludes.add(
        "com.alibaba.cloud.nacos.discovery.reactive.NacosReactiveDiscoveryClientConfiguration");
    excludes
        .add("com.alibaba.cloud.nacos.discovery.configclient.NacosConfigServerAutoConfiguration");
    excludes.add("com.alibaba.cloud.nacos.NacosServiceAutoConfiguration");

    return excludes;
  }

  public static Set<String> excludeToDiscovery() {
    excludes.add("com.alibaba.cloud.nacos.NacosConfigAutoConfiguration");
    excludes.add("com.alibaba.cloud.nacos.endpoint.NacosConfigEndpointAutoConfiguration");

    return excludes;
  }

  public static Set<String> excludeToNepxion() {
    excludes.add("com.nepxion.discovery.plugin.configcenter.configuration.ConfigAutoConfiguration");

    excludes.add("com.nepxion.discovery.common.nacos.configuration.NacosAutoConfiguration");
    excludes.add(
        "com.nepxion.discovery.plugin.configcenter.nacos.configuration.NacosConfigAutoConfiguration");

    excludes.add("com.nepxion.discovery.plugin.framework.configuration.PluginAutoConfiguration");

    excludes.add(
        "com.nepxion.discovery.plugin.registercenter.nacos.configuration.NacosAutoConfiguration");

    excludes.add("com.nepxion.discovery.plugin.strategy.configuration.StrategyAutoConfiguration");

    excludes.add(
        "com.nepxion.discovery.plugin.strategy.gateway.configuration.GatewayStrategyAutoConfiguration");
    excludes.add(
        "com.nepxion.discovery.plugin.strategy.gateway.configuration.GatewayStrategyContextAutoConfiguration");

    excludes.add(
        "com.nepxion.discovery.plugin.strategy.service.configuration.ServiceStrategyAutoConfiguration");
    excludes.add(
        "com.nepxion.discovery.plugin.strategy.service.configuration.ServiceStrategyContextAutoConfiguration");

    excludes.add("com.nepxion.discovery.console.configuration.ConsoleAutoConfiguration");

    return excludes;
  }
}
