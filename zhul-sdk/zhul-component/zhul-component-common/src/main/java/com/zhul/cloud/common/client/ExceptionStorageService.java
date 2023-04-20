package com.zhul.cloud.common.client;

/**
 * 异常存储服务
 * <p></p>
 * Created by yanglikai on 2021/8/30.
 */
public interface ExceptionStorageService {

  /**
   * 异常存储
   * <p></p>
   *
   * @param target
   */
  void storage(ExceptionStorage target);
}
