package com.zhul.cloud.feign;

import com.zhul.cloud.sentinel.OrderCenterServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yanglikai on 2020/12/11.
 */
@FeignClient(value = "zhul-order-center", fallbackFactory = OrderCenterServiceFallbackFactory.class)
public interface OrderCenterFeign {

  @RequestMapping(path = "/invoke", method = RequestMethod.POST)
  String invoke(@RequestBody String value);

  @RequestMapping(path = "/orders/{id}", method = RequestMethod.GET)
  String getOrder(@PathVariable(value = "id") Integer id);
}
