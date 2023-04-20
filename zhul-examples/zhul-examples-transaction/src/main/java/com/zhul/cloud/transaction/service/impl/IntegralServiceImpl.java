package com.zhul.cloud.transaction.service.impl;

import com.zhul.cloud.common.constant.ZhulConstant;
import com.zhul.cloud.transaction.mapper.mapper.IntegralMapper;
import com.zhul.cloud.transaction.service.IIntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * Created by yanglikai on 2021/1/13.
 */
@Service
@ConditionalOnProperty(name = ZhulConstant.SPRING_APPLICATION_NAME, havingValue = "seata-integral-center")
public class IntegralServiceImpl implements IIntegralService {

  @Autowired
  private IntegralMapper integralMapper;

  @Override
  public void debit(String userId, int money) {
    int count = integralMapper.addIntegral(userId, money);

    System.out.println("积分更新|受影响行数：" + count);
  }
}
