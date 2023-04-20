package com.zhul.cloud.transaction.service.impl;

import com.zhul.cloud.common.constant.ZhulConstant;
import com.zhul.cloud.common.exception.BizException;
import com.zhul.cloud.transaction.feign.IntegralFeignApi;
import com.zhul.cloud.transaction.feign.OrderFeignApi;
import com.zhul.cloud.transaction.feign.StorageFeignApi;
import com.zhul.cloud.transaction.mapper.entity.BizOrderDO;
import com.zhul.cloud.transaction.mapper.mapper.BizOrderMapper;
import com.zhul.cloud.transaction.service.IPayOrderService;
import io.seata.spring.annotation.GlobalTransactional;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * Created by yanglikai on 2021/1/15.
 */
@Slf4j
@Service
@ConditionalOnProperty(name = ZhulConstant.SPRING_APPLICATION_NAME, havingValue = "seata-biz-center")
public class PayOrderServiceImpl implements IPayOrderService {

  @Autowired
  private OrderFeignApi orderFeignApi;

  @Autowired
  private StorageFeignApi storageFeignApi;

  @Autowired
  private IntegralFeignApi integralFeignApi;

  @Autowired
  private BizOrderMapper bizOrderMapper;

  @GlobalTransactional(rollbackFor = Exception.class)
  @Override
  public void placeOrderToTCC4Success(Integer orderId) {
    BizOrderDO order = bizOrderMapper.selectById(orderId);
    Optional.of(order).orElseThrow(() -> new BizException("无效订单编号"));

    // 更新订单状态 -> 已支付
    orderFeignApi.updateOrder(order.getId());

    // 扣减库存
    storageFeignApi.tccDeduct(order.getProductCode(), order.getCount());

    // 增加积分
    integralFeignApi.tccDebit(order.getUserId(), order.getMoney());
  }

  @GlobalTransactional(rollbackFor = Exception.class)
  @Override
  public void placeOrderToTCC4Failure(Integer orderId) {
    placeOrderToTCC4Success(orderId);

    throw new BizException("订单支付失败");
  }
}
