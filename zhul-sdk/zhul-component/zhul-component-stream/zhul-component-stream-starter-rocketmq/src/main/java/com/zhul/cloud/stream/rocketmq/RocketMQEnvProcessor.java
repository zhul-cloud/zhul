package com.zhul.cloud.stream.rocketmq;

import com.zhul.cloud.env.ZhulEnvProcessor;

/**
 * Created by yanglikai on 2020/12/17.
 */
public class RocketMQEnvProcessor extends ZhulEnvProcessor {

  @Override
  public String getPrefixName() {
    return "rocketmq-config";
  }
}
