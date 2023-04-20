package com.zhul.cloud.lock.redis;

import com.zhul.cloud.env.ZhulEnvProcessor;

/**
 * Created by yanglikai on 2020/12/29.
 */
public class RedissonEnvProcessor extends ZhulEnvProcessor {

  @Override
  public String getPrefixName() {
    return "redisson-config";
  }
}
