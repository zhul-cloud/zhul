package com.zhul.cloud.transaction.feign;

import com.zhul.cloud.transaction.mapper.entity.OrderDO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by yanglikai on 2021/1/13.
 */
@FeignClient(value = "seata-order-center")
public interface OrderFeignApi {

  @PostMapping(value = "/v1/orders/create")
  OrderDO create(@RequestParam(value = "userID") String userId,
      @RequestParam(value = "productCode") String productCode,
      @RequestParam(value = "orderCount") int orderCount);

  @PostMapping(value = "/v1/orders/update/tcc")
  void updateOrder(@RequestParam(value = "orderId") Integer orderId);
}
