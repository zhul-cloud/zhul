package com.zhul.cloud.common.logger;

import java.util.Date;
import lombok.Data;

/**
 * 日志信息
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/9/28
 */
@Data
public class BizLoggerMessage {

  /**
   * 应用名
   */
  private String applicationName;
  /**
   * 类名方法名
   */
  private String method;
  /**
   * 追踪id
   */
  private String traceId;
  /**
   * 业务编码
   */
  private String bizCode;
  /**
   *
   */
  private String bizName;
  /**
   * 唯一标志
   */
  private String bizId;
  /**
   * 操作类型编码
   */
  private String optCode;
  /**
   *
   */
  private String optName;
  /**
   * 操作内容
   */
  private String optDetail;
  /**
   * 操作时间
   */
  private Date optTime;
  /**
   * 操作人id
   */
  private String optUserId;
  /**
   * 操作人
   */
  private String optUserName;
  /**
   * 入参
   */
  private String reqParams;
  /**
   * 出参
   */
  private String respParams;
  /**
   * 额外参数
   */
  private String ext;
  /**
   * 操作结果
   */
  private Integer result;
  /**
   * 异常内容
   */
  private String exception;
}
