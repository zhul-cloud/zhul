package com.zhul.cloud.common.logger;


import java.util.Map;

/**
 * 生成器
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/9/29
 */
public interface BizLoggerIdGenerator {

  /**
   * 业务id构造器
   *
   * @param metadata
   * @param reqParams
   * @param respParams
   * @return
   */
  String generate(BizLoggerMetadata metadata, Object[] reqParams, Object respParams,
      Map<String, Object> context);
}
