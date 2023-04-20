package com.zhul.cloud.common.model;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhul.cloud.common.exception.BizException;

/**
 * Created by yanglikai on 2019/5/28.
 */
public abstract class GeneralRequest extends GeneralObject {

  public <T> T tDO(Class<? extends GeneralObject> target) {
    return map(target);
  }

  public QueryWrapper tQueryWrapper() {
    throw new BizException("tQueryWrapper未实现");
  }
}
