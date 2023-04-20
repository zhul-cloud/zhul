package com.zhul.cloud.common.stream.client;

/**
 * 事件存储服务
 * <p></p>
 * Created by yanglikai on 2021/8/6.
 */
public interface EventStorageService {

  /**
   * 事件存储
   * <p></p>
   *
   * @param target
   */
  void storage(EventStorage target);
}
