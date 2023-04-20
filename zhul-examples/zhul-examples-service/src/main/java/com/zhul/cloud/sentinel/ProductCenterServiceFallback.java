package com.zhul.cloud.sentinel;

import com.zhul.cloud.feign.ProductCenterFeign;

/**
 * Created by yanglikai on 2020/12/14.
 */
public class ProductCenterServiceFallback implements ProductCenterFeign {

  private Throwable throwable;

  public ProductCenterServiceFallback(Throwable throwable) {
    this.throwable = throwable;
  }

  @Override
  public String invoke(String value) {
    return "consumer-fallback-default-str" + throwable.getMessage();
  }
}
