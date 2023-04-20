package com.zhul.cloud.transaction.service.impl;

import com.zhul.cloud.common.constant.ZhulConstant;
import com.zhul.cloud.transaction.mapper.mapper.StorageMapper;
import com.zhul.cloud.transaction.service.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * Created by yanglikai on 2021/1/13.
 */
@Service
@ConditionalOnProperty(name = ZhulConstant.SPRING_APPLICATION_NAME, havingValue = "seata-storage-center")
public class StorageServiceImpl implements IStorageService {

  @Autowired
  private StorageMapper storageMapper;

  @Override
  public void deduct(String productCode, int count) {
    storageMapper.reduceStock(productCode, count);

    System.out.println("库存更新|受影响行数：" + count);
  }
}
