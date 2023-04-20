package com.zhul.cloud.cache.spring.boot.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * memcached缓存配置
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/1/11
 */
@Configuration
@ConditionalOnProperty(value = "zhul.cloud.cache.memcached.enabled")
public class MemcachedConfig {
}
