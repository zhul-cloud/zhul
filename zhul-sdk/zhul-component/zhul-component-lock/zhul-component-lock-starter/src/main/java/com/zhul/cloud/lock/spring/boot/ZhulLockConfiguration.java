package com.zhul.cloud.lock.spring.boot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yanglikai on 2021/1/4.
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(ZhulLockProperties.class)
@ConditionalOnProperty(value = "zhul.cloud.lock.enabled", matchIfMissing = true)
public class ZhulLockConfiguration {

}
