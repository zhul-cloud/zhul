package com.zhul.cloud.sentinel;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Created by yanglikai on 2020/12/14.
 */
@Component
public class ProductCenterServiceFallbackFactory implements FallbackFactory {

  @Override
  public Object create(Throwable cause) {
    return new ProductCenterServiceFallback(cause);
  }
}
