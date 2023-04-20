package com.zhul.cloud.common.model;


import com.zhul.cloud.common.mapper.Mapper;

/**
 * Created by yanglikai on 2019/5/28.
 */
public abstract class GeneralObject extends PageObject {

  protected <T> T map(Class<? extends GeneralObject> target) {
    return Mapper.map(this, target);
  }
}
