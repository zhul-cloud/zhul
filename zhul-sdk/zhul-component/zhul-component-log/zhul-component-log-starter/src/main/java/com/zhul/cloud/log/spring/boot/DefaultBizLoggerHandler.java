package com.zhul.cloud.log.spring.boot;

import com.alibaba.fastjson.JSON;
import com.zhul.cloud.common.logger.BizLoggerHandler;
import com.zhul.cloud.common.logger.BizLoggerMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * 业务日志处理器
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/9/29
 */
@Slf4j
public class DefaultBizLoggerHandler implements BizLoggerHandler {

  @Override
  public void persistent(Object message) {
    log.info(JSON.toJSONString(message));
  }

  @Override
  public void persistent(BizLoggerMessage message) {
    this.persistent(message);
  }

  @Override
  public void persistent(BizLoggerMessage message, int methodLevel) {
    this.persistent(message);
  }

}
