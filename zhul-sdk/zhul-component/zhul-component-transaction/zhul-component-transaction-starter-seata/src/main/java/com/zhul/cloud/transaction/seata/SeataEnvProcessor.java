package com.zhul.cloud.transaction.seata;

import com.zhul.cloud.env.ZhulEnvProcessor;

/**
 * Created by yanglikai on 2020/12/17.
 */
public class SeataEnvProcessor extends ZhulEnvProcessor {

  @Override
  public String getPrefixName() {
    return "seata-config";
  }
}
