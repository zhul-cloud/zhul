package com.zhul.cloud.common.model;

/**
 * Created by yanglikai on 2019/5/28.
 */
public abstract class GeneralResponse extends GeneralObject {

  public <T> T tVO(Class<? extends GeneralObject> target) {
    return map(target);
  }
}
