package com.zhul.cloud.transaction.service.impl;

import com.zhul.cloud.common.constant.ZhulConstant;
import com.zhul.cloud.transaction.feign.OrderFeignApi;
import com.zhul.cloud.transaction.feign.StorageFeignApi;
import com.zhul.cloud.transaction.mapper.entity.BizOrderDO;
import com.zhul.cloud.transaction.mapper.mapper.BizOrderMapper;
import com.zhul.cloud.transaction.service.IPlaceOrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yanglikai on 2021/1/13.
 */
@Slf4j
@Service
@ConditionalOnProperty(name = ZhulConstant.SPRING_APPLICATION_NAME, havingValue = "seata-biz-center")
public class PlaceOrderServiceImpl implements IPlaceOrderService {

  @Autowired
  private StorageFeignApi storageFeignApi;

  @Autowired(required = false)
  private OrderFeignApi orderFeignApi;

  @Autowired
  private BizOrderMapper bizOrderMapper;

  @GlobalTransactional(rollbackFor = Exception.class)
  @Transactional(rollbackFor = Exception.class)
  @Override
  public void placeOrderToAT4Success(String userId, String productCode, int orderCount) {
    log.info("globalTransactional begin, Xid:{}", RootContext.getXID());

    // 本地保存
    localSave(userId, productCode, orderCount);

    // 库存扣减
    storageFeignApi.deduct(productCode, orderCount);

    // 生成订单
    orderFeignApi.create(userId, productCode, orderCount);
  }

  @GlobalTransactional(rollbackFor = Exception.class)
  @Transactional(rollbackFor = Exception.class)
  @Override
  public void placeOrderToAT1(String userId, String productCode, int orderCount) {
    log.info("globalTransactional begin, Xid:{}", RootContext.getXID());

    // 本地保存
    localSave(userId, productCode, orderCount);

    // 库存扣减
    storageFeignApi.deduct(productCode, orderCount);

    throw new RuntimeException("下单失败!");
  }

  @GlobalTransactional(rollbackFor = Exception.class)
  @Transactional(rollbackFor = Exception.class)
  @Override
  public void placeOrderToAT2(String userId, String productCode, int orderCount) {
    log.info("globalTransactional begin, Xid:{}", RootContext.getXID());

    // 本地保存
    localSave(userId, productCode, orderCount);

    // 库存扣减
    storageFeignApi.deduct(productCode, orderCount);

    // 生成订单
    orderFeignApi.create(userId, productCode, orderCount);

    throw new RuntimeException("下单失败!");
  }

  private void localSave(String userId, String productCode, int orderCount) {
    BizOrderDO order = new BizOrderDO();
    order.setUserId(userId);
    order.setProductCode(productCode);
    order.setCount(orderCount);
    order.setMoney(320);

    bizOrderMapper.insert(order);
  }
}
