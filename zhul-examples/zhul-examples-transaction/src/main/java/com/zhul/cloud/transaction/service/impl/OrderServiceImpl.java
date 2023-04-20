package com.zhul.cloud.transaction.service.impl;

import com.zhul.cloud.common.constant.ZhulConstant;
import com.zhul.cloud.transaction.mapper.entity.OrderDO;
import com.zhul.cloud.transaction.mapper.mapper.OrderMapper;
import com.zhul.cloud.transaction.service.IOrderService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * Created by yanglikai on 2021/1/13.
 */
@Service
@ConditionalOnProperty(name = ZhulConstant.SPRING_APPLICATION_NAME, havingValue = "seata-order-center")
public class OrderServiceImpl implements IOrderService {

  private static final Map<String, Integer> productPrices = new HashMap<>();

  @Autowired
  private OrderMapper orderMapper;

  static {
    productPrices.put("1", 2);
    productPrices.put("2", 4);
    productPrices.put("3", 6);
    productPrices.put("4", 8);
    productPrices.put("5", 10);
  }

  @Override
  public OrderDO create(String userId, String productCode, int orderCount) {

    int orderMoney = calc(productCode, orderCount);

    OrderDO order = new OrderDO();
    order.setUserId(userId);
    order.setProductCode(productCode);
    order.setCount(orderCount);
    order.setMoney(orderMoney);

    orderMapper.insert(order);
    return order;
  }

  @Override
  public int calc(String productCode, int orderCount) {
    Integer price = productPrices.getOrDefault(productCode, 3);
    return price * orderCount;
  }
}
