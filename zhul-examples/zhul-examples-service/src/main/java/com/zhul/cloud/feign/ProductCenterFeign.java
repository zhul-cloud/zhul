package com.zhul.cloud.feign;

import com.zhul.cloud.sentinel.ProductCenterServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yanglikai on 2020/12/11.
 */
@FeignClient(value = "zhul-product-center", fallbackFactory = ProductCenterServiceFallbackFactory.class)
public interface ProductCenterFeign {

  @RequestMapping(path = "/invoke", method = RequestMethod.POST)
  String invoke(@RequestBody String value);
}
