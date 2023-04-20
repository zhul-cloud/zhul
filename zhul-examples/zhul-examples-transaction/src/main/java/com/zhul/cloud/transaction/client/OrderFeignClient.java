package com.zhul.cloud.transaction.client;

import com.zhul.cloud.common.constant.ZhulConstant;
import com.zhul.cloud.common.exception.BizException;
import com.zhul.cloud.transaction.feign.IntegralFeignApi;
import com.zhul.cloud.transaction.feign.OrderFeignApi;
import com.zhul.cloud.transaction.mapper.entity.OrderDO;
import com.zhul.cloud.transaction.service.IOrderService;
import com.zhul.cloud.transaction.tcc.IUpdateOrderTCCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yanglikai on 2021/1/13.
 */
@RestController
@ConditionalOnProperty(name = ZhulConstant.SPRING_APPLICATION_NAME, havingValue = "seata-order-center")
public class OrderFeignClient implements OrderFeignApi {

  @Autowired
  private IntegralFeignApi integralFeignApi;

  @Autowired
  private IOrderService orderService;

  @Autowired
  private IUpdateOrderTCCService updateOrderTCCService;

  @Override
  public OrderDO create(String userId, String productCode, int orderCount) {
    int orderMoney = orderService.calc(productCode, orderCount);

    integralFeignApi.debit(userId, orderMoney);

    return orderService.create(userId, productCode, orderCount);
  }

  @Override
  public void updateOrder(Integer orderId) {
    updateOrderTCCService.prepare(orderId);
  }
}
