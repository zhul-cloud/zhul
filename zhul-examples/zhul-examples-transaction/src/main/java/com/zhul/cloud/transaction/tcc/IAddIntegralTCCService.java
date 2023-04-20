package com.zhul.cloud.transaction.tcc;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * 增加积分TCC
 * Created by yanglikai on 2021/1/15.
 */
@LocalTCC
public interface IAddIntegralTCCService {

  @TwoPhaseBusinessAction(name = "addIntegralTCCService")
  void prepare(@BusinessActionContextParameter(paramName = "userId") String userId,
      @BusinessActionContextParameter(paramName = "money") int money);

  boolean commit(BusinessActionContext context);

  boolean rollback(BusinessActionContext context);
}
