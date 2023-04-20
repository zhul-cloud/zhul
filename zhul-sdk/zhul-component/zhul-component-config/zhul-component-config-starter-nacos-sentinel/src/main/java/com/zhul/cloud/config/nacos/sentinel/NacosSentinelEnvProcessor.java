package com.zhul.cloud.config.nacos.sentinel;

import com.zhul.cloud.env.ZhulEnvProcessor;

/**
 * Created by yanglikai on 2020/12/17.
 */
public class NacosSentinelEnvProcessor extends ZhulEnvProcessor {

  @Override
  public String getPrefixName() {
    return "nacos-sentinel-config";
  }
}
