package com.zhul.cloud.common.stream.evnet;

/**
 * 事件消息
 * <p></p>
 * Created by yanglikai on 2021/5/28.
 */
public interface EventMessage {

  /**
   * 消息键
   * <p></p>
   *
   * @return 消息键
   */
  String key();

  /**
   * 字符串转换
   * <p></p>
   *
   * @return
   */
  String tString();
}
