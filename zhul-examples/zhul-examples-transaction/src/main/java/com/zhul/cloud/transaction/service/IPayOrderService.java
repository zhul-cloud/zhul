package com.zhul.cloud.transaction.service;

/**
 * 订单支付服务
 * Created by yanglikai on 2021/1/15.
 */
public interface IPayOrderService {

  void placeOrderToTCC4Success(Integer orderId);

  void placeOrderToTCC4Failure(Integer orderId);
}
