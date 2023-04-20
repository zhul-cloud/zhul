package com.zhul.cloud.common.logger;

import java.util.Map;
import lombok.Getter;
import org.springframework.lang.Nullable;

/**
 * 业务日志上下文
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/9/25
 */
@Getter
public class BizLoggerContextHolder {

  private BizLoggerMetadata metadata;

  private final Object[] reqParams;
  private final Object respParams;
  private final Map<String, Object> context;

  public String generateBizId() {
    return this.metadata.getBizLoggerIdGenerator()
        .generate(metadata, reqParams, respParams, context);
  }

  public Object generateMessage(@Nullable BizLoggerMessage message) {

    return this.metadata.getBizLoggerMessageGenerator()
        .generate(message, metadata, reqParams, respParams, context);
  }

  public void persistent(Object message) {
    this.metadata.getBizLoggerHandler().persistent(message);
  }

  public BizLoggerContextHolder(BizLoggerMetadata metadata,
      Object[] reqParams, Object respParams, Map<String, Object> context) {
    this.reqParams = reqParams;
    this.respParams = respParams;
    this.metadata = metadata;
    this.context = context;
  }
}
