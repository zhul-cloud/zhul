package com.zhul.cloud.common.client;

/**
 * 请求存储服务
 * <p></p>
 * Created by yanglikai on 2021/9/03.
 */
public interface RequestStorageService {

  /**
   * 请求存储
   * <p></p>
   *
   * @param target
   */
  void storage(RequestStorage target);
}
