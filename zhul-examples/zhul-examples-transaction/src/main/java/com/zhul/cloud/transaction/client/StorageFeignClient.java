package com.zhul.cloud.transaction.client;

import com.zhul.cloud.common.constant.ZhulConstant;
import com.zhul.cloud.transaction.feign.StorageFeignApi;
import com.zhul.cloud.transaction.service.IStorageService;
import com.zhul.cloud.transaction.tcc.IReduceStockTCCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yanglikai on 2021/1/13.
 */
@RestController
@ConditionalOnProperty(name = ZhulConstant.SPRING_APPLICATION_NAME, havingValue = "seata-storage-center")
public class StorageFeignClient implements StorageFeignApi {

  @Autowired
  private IStorageService storageService;

  @Autowired
  private IReduceStockTCCService reduceStockTCCService;

  @Override
  public void deduct(String productCode, int count) {
    storageService.deduct(productCode, count);
  }

  @Override
  public void tccDeduct(String productCode, int count) {
    reduceStockTCCService.prepare(productCode, count);
  }
}
