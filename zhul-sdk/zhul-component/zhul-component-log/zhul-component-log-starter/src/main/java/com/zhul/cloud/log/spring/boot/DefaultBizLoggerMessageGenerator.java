package com.zhul.cloud.log.spring.boot;

import com.zhul.cloud.common.logger.BizLoggerMessage;
import com.zhul.cloud.common.logger.BizLoggerMessageGenerator;
import com.zhul.cloud.common.logger.BizLoggerMetadata;
import java.util.Map;

/**
 * 默认生成器
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/9/29
 */
public class DefaultBizLoggerMessageGenerator implements BizLoggerMessageGenerator {


  @Override
  public Object generate(BizLoggerMessage message, BizLoggerMetadata metadata, Object[] reqParams,
      Object respParams, Map<String, Object> context) {
    return message;
  }
}