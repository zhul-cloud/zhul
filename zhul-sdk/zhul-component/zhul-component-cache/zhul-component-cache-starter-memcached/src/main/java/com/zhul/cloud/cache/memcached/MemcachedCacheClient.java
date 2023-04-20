package com.zhul.cloud.cache.memcached;

import com.zhul.cloud.common.api.CacheProvider;

/**
 * Created by yanglikai on 2021/1/4.
 */
public class MemcachedCacheClient implements CacheProvider {

  @Override
  public void deleteKey(String key) {

  }

  @Override
  public void set(String key, String value, long timeout) {

  }

  @Override
  public <T> void set(String key, T value, long timeout) {

  }

  @Override
  public String get(String key) {
    return null;
  }

  @Override
  public <T> T get(String key, Class<T> clazz) {
    return null;
  }
}
