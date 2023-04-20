package com.zhul.cloud.transaction.service;

/**
 * 积分服务
 * Created by yanglikai on 2021/1/13.
 */
public interface IIntegralService {

  void debit(String userId, int money);
}
