package com.zhul.cloud.cache.spring.boot.config;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.util.StringUtils;

/**
 * reids缓存配置
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/1/11
 */
@Configuration
@ConditionalOnProperty(value = {"zhul.cloud.cache.enabled",
    "zhul.cloud.cache.redis"}, havingValue = "true")
public class RedisConfig {

  @Autowired
  private RedisConnectionFactory redisConnectionFactory;
  @Value("${spring.cache.redis.key-prefix}")
  private String keyPrefix;
  @Value("${spring.cache.redis.time-to-live}")
  private Long timeToLive;
  @Value("${spring.cache.redis.cache-null-values:true}")
  private boolean cacheNullValues;

  @Bean
  public CacheManager cacheManager() {
    RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
        .entryTtl(Duration.ofMillis(timeToLive))
        .prefixCacheNameWith(keyPrefix);
    if (!cacheNullValues) {
      config.disableCachingNullValues();
    }

    MyRedisCacheManager redisCacheManager = new MyRedisCacheManager(
        RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory), config);
    return redisCacheManager;
  }


}

class MyRedisCacheManager extends RedisCacheManager {

  public MyRedisCacheManager(RedisCacheWriter cacheWriter,
      RedisCacheConfiguration defaultCacheConfiguration) {
    super(cacheWriter, defaultCacheConfiguration);
  }

  @Override
  protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
    String[] array = StringUtils.delimitedListToStringArray(name, "#");
    name = array[0];
    if (array.length > 1) {
      long ttl = Long.parseLong(array[1]);
      /**
       * ttl单位为秒
       */
      cacheConfig = cacheConfig.entryTtl(Duration.ofSeconds(ttl));
    }
    return super.createRedisCache(name, cacheConfig);
  }

}