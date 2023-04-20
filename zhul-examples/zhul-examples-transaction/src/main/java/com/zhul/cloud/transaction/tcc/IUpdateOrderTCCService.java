package com.zhul.cloud.transaction.tcc;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * 更新订单TCC
 * Created by yanglikai on 2021/1/15.
 */
@LocalTCC
public interface IUpdateOrderTCCService {

  @TwoPhaseBusinessAction(name = "orderCreateTCCService")
  void prepare(@BusinessActionContextParameter(paramName = "orderId") Integer orderId);

  boolean commit(BusinessActionContext context);

  boolean rollback(BusinessActionContext context);
}
