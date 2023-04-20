package com.zhul.cloud.common.api;

/**
 * Created by yanglikai on 2020/12/31.
 */
public interface CacheProvider {

  void deleteKey(String key);

  void set(String key, String value, long timeout);

  <T> void set(String key, T value, long timeout);

  String get(String key);

  <T> T get(String key, Class<T> clazz);
}
