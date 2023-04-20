package com.zhul.cloud.transaction.service;

/**
 * 下单服务
 * Created by yanglikai on 2021/1/13.
 */
public interface IPlaceOrderService {

  void placeOrderToAT4Success(String userId, String productCode, int orderCount);

  void placeOrderToAT1(String userId, String productCode, int orderCount);

  void placeOrderToAT2(String userId, String productCode, int orderCount);
}
