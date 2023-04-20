package com.zhul.cloud.transaction.client;

import com.zhul.cloud.common.constant.ZhulConstant;
import com.zhul.cloud.transaction.feign.IntegralFeignApi;
import com.zhul.cloud.transaction.service.IIntegralService;
import com.zhul.cloud.transaction.tcc.IAddIntegralTCCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yanglikai on 2021/1/13.
 */
@RestController
@ConditionalOnProperty(name = ZhulConstant.SPRING_APPLICATION_NAME, havingValue = "seata-integral-center")
public class IntegralFeignClient implements IntegralFeignApi {

  @Autowired
  private IIntegralService integralService;

  @Autowired
  private IAddIntegralTCCService addIntegralTCCService;

  @Override
  public void debit(String userId, int money) {
    integralService.debit(userId, money);
  }

  @Override
  public void tccDebit(String userId, int money) {
    addIntegralTCCService.prepare(userId, money);
  }
}
