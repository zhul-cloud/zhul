package com.zhul.cloud.transaction.tcc.impl;

import com.zhul.cloud.common.constant.ZhulConstant;
import com.zhul.cloud.common.exception.BizException;
import com.zhul.cloud.transaction.enums.OrderStatus;
import com.zhul.cloud.transaction.mapper.entity.OrderDO;
import com.zhul.cloud.transaction.mapper.mapper.OrderMapper;
import com.zhul.cloud.transaction.tcc.IUpdateOrderTCCService;
import io.seata.rm.tcc.api.BusinessActionContext;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * Created by yanglikai on 2021/1/15.
 */
@Service
@ConditionalOnProperty(name = ZhulConstant.SPRING_APPLICATION_NAME, havingValue = "seata-order-center")
public class UpdateOrderTCCServiceImpl implements IUpdateOrderTCCService {

  @Autowired
  private OrderMapper orderMapper;

  @Override
  public void prepare(Integer orderId) {
    OrderDO order = orderMapper.selectById(orderId);
    Optional.of(order).orElseThrow(() -> new BizException("无效订单编号"));

    // 更新订单状态 -> 支付待更新
    order.setStatus(OrderStatus.PAY_UPDATING.code);
    orderMapper.updateById(order);
  }

  @Override
  public boolean commit(BusinessActionContext context) {
    int orderId = (int) context.getActionContext("orderId");

    OrderDO order = orderMapper.selectById(orderId);
    Optional.of(order).orElseThrow(() -> new BizException("无效订单编号"));

    // 更新订单状态 -> 已支付
    order.setStatus(OrderStatus.PAYED.code);
    orderMapper.updateById(order);

    return true;
  }

  @Override
  public boolean rollback(BusinessActionContext context) {
    int orderId = (int) context.getActionContext("orderId");

    OrderDO order = orderMapper.selectById(orderId);
    Optional.of(order).orElseThrow(() -> new BizException("无效订单编号"));

    // 更新订单状态 -> 待支付
    order.setStatus(OrderStatus.PAYING.code);
    orderMapper.updateById(order);

    return true;
  }
}
