package com.zhul.cloud.common.logger;

/**
 * 业务日志处理器
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/9/29
 */
public interface BizLoggerHandler {

  /**
   * 持久化方法
   *
   * @param message
   */
  void persistent(Object message);

  /**
   * 持久化方法（用于实现类自动补全应用名、方法名、操作者、操作时间等）
   *
   * @param message
   */
  void persistent(BizLoggerMessage message);

  /**
   * 持久化方法（用于实现类自动补全应用名、方法名、操作者、操作时间等）,stackTraceLevel获取方法名的位置
   *
   * @param message
   */
  void persistent(BizLoggerMessage message, int stackTraceLevel);
}
