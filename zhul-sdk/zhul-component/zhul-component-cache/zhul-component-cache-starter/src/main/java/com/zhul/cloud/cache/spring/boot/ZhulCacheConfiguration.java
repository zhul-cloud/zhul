package com.zhul.cloud.cache.spring.boot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yanglikai on 2021/1/4.
 */
@EnableCaching
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(ZhulCacheProperties.class)
@ConditionalOnProperty(value = "zhul.cloud.cache.enabled", matchIfMissing = true)
public class ZhulCacheConfiguration {

}
