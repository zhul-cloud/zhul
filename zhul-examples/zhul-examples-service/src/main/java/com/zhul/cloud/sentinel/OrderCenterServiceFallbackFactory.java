package com.zhul.cloud.sentinel;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Created by yanglikai on 2020/12/14.
 */
@Component
public class OrderCenterServiceFallbackFactory implements FallbackFactory<OrderCenterServiceFallback> {
  @Override
  public OrderCenterServiceFallback create(Throwable throwable) {
    return new OrderCenterServiceFallback(throwable);
  }
}
