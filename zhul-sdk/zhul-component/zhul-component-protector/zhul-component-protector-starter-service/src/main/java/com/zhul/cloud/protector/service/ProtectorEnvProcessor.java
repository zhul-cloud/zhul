package com.zhul.cloud.protector.service;

import com.zhul.cloud.env.ZhulEnvProcessor;

/**
 * Created by yanglikai on 2020/12/17.
 */
public class ProtectorEnvProcessor extends ZhulEnvProcessor {

  @Override
  public String getPrefixName() {
    return "protector-config";
  }
}
