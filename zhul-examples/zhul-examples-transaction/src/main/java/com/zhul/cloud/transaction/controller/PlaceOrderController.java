package com.zhul.cloud.transaction.controller;

import com.zhul.cloud.common.constant.ZhulConstant;
import com.zhul.cloud.common.model.CRUResponse;
import com.zhul.cloud.transaction.service.IPlaceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yanglikai on 2021/1/13.
 */
@RestController
@ConditionalOnProperty(name = ZhulConstant.SPRING_APPLICATION_NAME, havingValue = "seata-biz-center")
public class PlaceOrderController {

  @Autowired
  private IPlaceOrderService bizService;

  @PostMapping(value = "/v1/shop/place/order/at/success")
  public CRUResponse placeOrderToAT4Success(String userId, String productCode, int orderCount) {
    bizService.placeOrderToAT4Success(userId, productCode, orderCount);

    return new CRUResponse();
  }

  @PostMapping(value = "/v1/shop/place/order/at/one")
  public CRUResponse placeOrderToAT1(String userId, String productCode, int orderCount) {
    bizService.placeOrderToAT1(userId, productCode, orderCount);

    return new CRUResponse();
  }

  @PostMapping(value = "/v1/shop/place/order/at/two")
  public CRUResponse placeOrderToAT2(String userId, String productCode, int orderCount) {
    bizService.placeOrderToAT2(userId, productCode, orderCount);

    return new CRUResponse();
  }
}
