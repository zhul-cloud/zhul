package com.zhul.cloud.log.spring.boot;

import com.alibaba.fastjson.JSON;
import com.zhul.cloud.common.logger.BizLoggerIdGenerator;
import com.zhul.cloud.common.logger.BizLoggerMetadata;
import java.util.Map;
import org.springframework.expression.EvaluationContext;
import org.springframework.util.StringUtils;

/**
 * 默认生成器
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/9/29
 */
public class DefaultBizLoggerIdGenerator implements BizLoggerIdGenerator {

  private final BizLoggerExpressionEvaluator evaluator = new BizLoggerExpressionEvaluator();

  /**
   * Generate a key based on the specified parameters.
   */
  @Override
  public String generate(BizLoggerMetadata metadata, Object[] reqParams, Object respParams,
      Map<String, Object> context) {
    if (StringUtils.hasText(metadata.getAnnotation().bizId())) {
      EvaluationContext evaluationContext = evaluator
          .createEvaluationContext(metadata.getTargetClass(), metadata.getTargetMethod(), reqParams,
              respParams);
      Object key = evaluator
          .key(metadata.getAnnotation().bizId(), metadata.getMethodKey(), evaluationContext);
      return JSON.toJSONString(key);
    }

    if (reqParams.length == 0) {
      throw new RuntimeException("无参方法无法获取bizId");
    }
    if (reqParams.length == 1) {
      Object param = reqParams[0];
      if (param != null && param instanceof String) {
        return (String) param;
      } else {
        return JSON.toJSONString(param);
      }
    }
    return JSON.toJSONString(reqParams);
  }

}