package com.zhul.cloud.cache.j2cache;

import com.zhul.cloud.env.ZhulEnvProcessor;

/**
 * j2cache
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/9/29
 */
public class J2CacheEnvProcessor extends ZhulEnvProcessor {

  @Override
  public String getPrefixName() {
    return "j2cache-config";
  }

}
