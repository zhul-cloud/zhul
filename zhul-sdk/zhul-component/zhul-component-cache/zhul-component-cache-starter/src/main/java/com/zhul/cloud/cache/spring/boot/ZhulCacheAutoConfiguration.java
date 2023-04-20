package com.zhul.cloud.cache.spring.boot;

import com.zhul.cloud.cache.j2cache.J2CacheClient;
import com.zhul.cloud.cache.memcached.MemcachedCacheClient;
import com.zhul.cloud.cache.redis.RedisCacheClient;
import com.zhul.cloud.cache.spring.boot.config.J2CacheConfig;
import com.zhul.cloud.cache.spring.boot.config.MemcachedConfig;
import com.zhul.cloud.cache.spring.boot.config.RedisConfig;
import com.zhul.cloud.common.api.CacheProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Created by yanglikai on 2021/1/4.
 */
@Configuration(proxyBeanMethods = false)
@Import({ZhulCacheConfiguration.class, J2CacheConfig.class, RedisConfig.class,
    MemcachedConfig.class})
@ConditionalOnProperty(value = "zhul.cloud.cache.enabled", matchIfMissing = true)
public class ZhulCacheAutoConfiguration {

  @Autowired(required = false)
  private StringRedisTemplate redisTemplate;

  @Bean(value = "redisCacheProvider")
  @ConditionalOnProperty(value = "zhul.cloud.cache.j2cache.enabled", matchIfMissing = true)
  public CacheProvider j2CacheProvider() {
    return new J2CacheClient(redisTemplate);
  }

  @Bean(value = "redisCacheProvider")
  @ConditionalOnProperty(value = {"zhul.cloud.cache.redis.enabled"})
  public CacheProvider redisCacheProvider() {
    return new RedisCacheClient(redisTemplate);
  }

  @Bean(value = "memcachedCacheProvider")
  @ConditionalOnProperty(value = "zhul.cloud.cache.memcached.enabled")
  public CacheProvider memcachedCacheProvider() {
    return new MemcachedCacheClient();
  }
}
