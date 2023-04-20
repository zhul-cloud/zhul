package com.zhul.cloud.tracing.skywalking;

import com.zhul.cloud.env.ZhulEnvProcessor;

/**
 * Created by yanglikai on 2020/12/17.
 */
public class SkywalkingEnvProcessor extends ZhulEnvProcessor {

  @Override
  public String getPrefixName() {
    return "skywalking-config";
  }
}
