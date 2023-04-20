package com.zhul.cloud.database.sharding;

import com.zhul.cloud.env.ZhulEnvProcessor;

/**
 * Created by yanglikai on 2021/1/26.
 */
public class ShardingEnvProcessor extends ZhulEnvProcessor {

  @Override
  public String getPrefixName() {
    return "sharding-config";
  }
}
