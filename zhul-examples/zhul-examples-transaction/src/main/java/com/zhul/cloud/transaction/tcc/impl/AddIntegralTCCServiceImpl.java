package com.zhul.cloud.transaction.tcc.impl;

import com.zhul.cloud.common.constant.ZhulConstant;
import com.zhul.cloud.transaction.mapper.mapper.IntegralMapper;
import com.zhul.cloud.transaction.tcc.IAddIntegralTCCService;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * Created by yanglikai on 2021/1/15.
 */
@Service
@ConditionalOnProperty(name = ZhulConstant.SPRING_APPLICATION_NAME, havingValue = "seata-integral-center")
public class AddIntegralTCCServiceImpl implements IAddIntegralTCCService {

  @Autowired
  private IntegralMapper integralMapper;

  @Override
  public void prepare(String userId, int money) {
    // 冻结积分
    integralMapper.frozenIntegral(userId, money);
  }

  @Override
  public boolean commit(BusinessActionContext context) {
    String userId = (String) context.getActionContext("userId");
    int money = (int) context.getActionContext("money");

    // 1.增加积分
    integralMapper.addIntegral(userId, money);

    // 2.取消冻结
    integralMapper.cancelFrozen(userId, money);

    return true;
  }

  @Override
  public boolean rollback(BusinessActionContext context) {
    String userId = (String) context.getActionContext("userId");
    int money = (int) context.getActionContext("money");

    // 取消冻结
    integralMapper.cancelFrozen(userId, money);

    return true;
  }
}
