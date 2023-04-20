package com.zhul.cloud.transaction.service;

/**
 * 仓库服务
 * Created by yanglikai on 2021/1/13.
 */
public interface IStorageService {

  void deduct(String productCode, int count);
}
