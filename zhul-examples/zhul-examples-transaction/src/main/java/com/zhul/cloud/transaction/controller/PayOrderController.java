package com.zhul.cloud.transaction.controller;

import com.zhul.cloud.common.constant.ZhulConstant;
import com.zhul.cloud.common.model.CRUResponse;
import com.zhul.cloud.transaction.service.IPayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yanglikai on 2021/1/13.
 */
@RestController
@ConditionalOnProperty(name = ZhulConstant.SPRING_APPLICATION_NAME, havingValue = "seata-biz-center")
public class PayOrderController {

  @Autowired
  private IPayOrderService payOrderService;

  @PostMapping(value = "/v1/shop/pay/order/tcc/success")
  public CRUResponse payOrderToTCC4Success(Integer orderId) {
    payOrderService.placeOrderToTCC4Success(orderId);

    return new CRUResponse();
  }

  @PostMapping(value = "/v1/shop/pay/order/tcc/failure")
  public CRUResponse placeOrderToTCC4Failure(Integer orderId) {
    payOrderService.placeOrderToTCC4Failure(orderId);

    return new CRUResponse();
  }
}
