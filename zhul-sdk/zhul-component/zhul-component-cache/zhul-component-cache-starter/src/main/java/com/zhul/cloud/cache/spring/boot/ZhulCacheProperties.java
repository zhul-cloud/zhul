package com.zhul.cloud.cache.spring.boot;

import com.zhul.cloud.cache.spring.boot.properties.J2CacheProperties;
import com.zhul.cloud.cache.spring.boot.properties.MemcachedProperties;
import com.zhul.cloud.cache.spring.boot.properties.RedisProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by yanglikai on 2021/1/4.
 */
@Data
@ConfigurationProperties(value = "zhul.cloud.cache")
public class ZhulCacheProperties {

  private boolean enabled = true;

  private RedisProperties redis;

  private MemcachedProperties memcached;

  private J2CacheProperties j2cache;

}
