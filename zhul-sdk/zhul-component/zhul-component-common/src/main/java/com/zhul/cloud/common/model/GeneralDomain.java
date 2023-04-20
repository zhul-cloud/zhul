package com.zhul.cloud.common.model;

/**
 * Created by yanglikai on 2019/5/28.
 */
public abstract class GeneralDomain extends GeneralObject {

  public <T> T tRequest(Class<? extends GeneralObject> target) {
    return map(target);
  }

  public GeneralDTO tDTO(Class<? extends GeneralObject> target) {
    return map(target);
  }
}
