package com.zhul.cloud.sentinel;

import com.zhul.cloud.feign.OrderCenterFeign;

/**
 * Created by yanglikai on 2020/12/14.
 */
public class OrderCenterServiceFallback implements OrderCenterFeign {

  private Throwable throwable;

  public OrderCenterServiceFallback(Throwable throwable) {
    this.throwable = throwable;
  }

  @Override
  public String invoke(String value) {
    return "consumer-fallback-default-str" + throwable.getMessage();
  }

  @Override
  public String getOrder(Integer id) {
    return "consumer-fallback-default-str" + throwable.getMessage();
  }
}
