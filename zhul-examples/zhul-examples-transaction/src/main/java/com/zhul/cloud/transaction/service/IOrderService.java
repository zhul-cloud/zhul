package com.zhul.cloud.transaction.service;

import com.zhul.cloud.transaction.mapper.entity.OrderDO;

/**
 * 订单服务
 * Created by yanglikai on 2021/1/13.
 */
public interface IOrderService {

  OrderDO create(String userId, String productCode, int orderCount);

  int calc(String productCode, int orderCount);
}
