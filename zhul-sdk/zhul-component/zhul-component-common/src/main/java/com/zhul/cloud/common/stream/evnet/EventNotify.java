package com.zhul.cloud.common.stream.evnet;

/**
 * 事件通知
 * <p>
 * </p>
 * Created by yanglikai on 2021/4/28.
 */
public interface EventNotify<T extends Event> {

  /**
   * 事件发送
   * <p></p>
   *
   * @param event 事件对象
   */
  void send(T event);
}
