package com.zhul.cloud.common.logger;


import java.util.Map;

/**
 * 生成器
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/9/29
 */
public interface BizLoggerMessageGenerator {

  /**
   * 消息构造器
   *
   * @param message
   * @param metadata
   * @param reqParams
   * @param respParams
   * @param context
   * @return
   */
  Object generate(BizLoggerMessage message, BizLoggerMetadata metadata, Object[] reqParams,
      Object respParams, Map<String, Object> context);

}
