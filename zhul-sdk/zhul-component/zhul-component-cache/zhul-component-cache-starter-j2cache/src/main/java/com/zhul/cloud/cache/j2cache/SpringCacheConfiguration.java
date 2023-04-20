package com.zhul.cloud.cache.j2cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;
import org.springframework.context.annotation.Configuration;

/**
 * spring cache 失败处理
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/9/29
 */
@Configuration
public class SpringCacheConfiguration extends CachingConfigurerSupport {

  /**
   * 如果反序列化失败则将缓存中的数据删除，并重新走数据库(一般是由于数据模型变化导致反序列化失败)。
   */
  @Slf4j
  private static class RelaxedCacheErrorHandler extends SimpleCacheErrorHandler {

    @Override
    public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
      cache.evict(key); // 失效缓存。
      log.error(
          String.format("Error getting from cache, cacheName = %s, key = %s ,evict it.",
              cache.getName(), key),
          exception);
    }
  }

  @Override
  public CacheErrorHandler errorHandler() {
    return new RelaxedCacheErrorHandler();
  }
}