package com.zhul.cloud.cache.redis;

import com.zhul.cloud.env.ZhulEnvProcessor;

/**
 * Created by yanglikai on 2020/12/29.
 */
public class RedisEnvProcessor extends ZhulEnvProcessor {

  @Override
  public String getPrefixName() {
    return "redis-config";
  }
}
