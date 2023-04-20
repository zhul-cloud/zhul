package com.zhul.cloud.transaction.tcc;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * 扣减库存TCC
 * Created by yanglikai on 2021/1/15.
 */
@LocalTCC
public interface IReduceStockTCCService {

  @TwoPhaseBusinessAction(name = "reduceStockTCCService")
  void prepare(@BusinessActionContextParameter(paramName = "productCode") String productCode,
      @BusinessActionContextParameter(paramName = "count") int count);

  boolean commit(BusinessActionContext context);

  boolean rollback(BusinessActionContext context);
}
