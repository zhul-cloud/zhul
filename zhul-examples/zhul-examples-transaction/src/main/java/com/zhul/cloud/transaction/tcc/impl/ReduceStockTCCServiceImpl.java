package com.zhul.cloud.transaction.tcc.impl;

import com.zhul.cloud.common.constant.ZhulConstant;
import com.zhul.cloud.transaction.mapper.mapper.StorageMapper;
import com.zhul.cloud.transaction.tcc.IReduceStockTCCService;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * Created by yanglikai on 2021/1/15.
 */
@Service
@ConditionalOnProperty(name = ZhulConstant.SPRING_APPLICATION_NAME, havingValue = "seata-storage-center")
public class ReduceStockTCCServiceImpl implements IReduceStockTCCService {

  @Autowired
  private StorageMapper storageMapper;

  @Override
  public void prepare(String productCode, int count) {
    // 冻结库存
    storageMapper.frozenStock(productCode, count);
  }

  @Override
  public boolean commit(BusinessActionContext context) {
    String productCode = (String) context.getActionContext("productCode");
    int count = (int) context.getActionContext("count");

    // 1.扣减库存
    storageMapper.reduceStock(productCode, count);

    // 2.取消冻结
    storageMapper.cancelFrozen(productCode, count);

    return true;
  }

  @Override
  public boolean rollback(BusinessActionContext context) {
    String productCode = (String) context.getActionContext("productCode");
    int count = (int) context.getActionContext("count");

    // 取消冻结
    storageMapper.cancelFrozen(productCode, count);

    return true;
  }
}
