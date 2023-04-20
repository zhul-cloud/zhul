package com.zhul.cloud.cache.j2cache;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.zhul.cloud.common.api.CacheProvider;
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * j2cache
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/9/29
 */
public class J2CacheClient implements CacheProvider {

  private StringRedisTemplate redisTemplate;

  public J2CacheClient(StringRedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @Override
  public void deleteKey(String key) {
    redisTemplate.delete(key);
  }

  @Override
  public void set(String key, String value, long timeout) {
    redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
  }

  @Override
  public <T> void set(String key, T value, long timeout) {
    redisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value), timeout, TimeUnit.SECONDS);
  }

  @Override
  public String get(String key) {
    String value = redisTemplate.opsForValue().get(key);
    return value;
  }

  @Override
  public <T> T get(String key, Class<T> clazz) {
    String value = redisTemplate.opsForValue().get(key);

    return StrUtil.isNotBlank(value) ? JSONUtil.toBean(value, clazz) : null;
  }
}
