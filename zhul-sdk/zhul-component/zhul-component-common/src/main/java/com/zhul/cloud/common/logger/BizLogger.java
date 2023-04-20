package com.zhul.cloud.common.logger;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 业务日志注解
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/9/25
 */
@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface BizLogger {

  /**
   * 业务模块编码
   *
   * @return
   */
  String bizCode();

  /**
   * 业务模块名称
   *
   * @return
   */
  String bizName() default "";

  /**
   * 业务id，支持el表达式
   *
   * @return
   */
  String bizId();

  /**
   * 业务操作编码
   *
   * @return
   */
  String optCode();

  /**
   * 业务操作[名称
   *
   * @return
   */
  String optName() default "";

  /**
   * BizLoggerIdGenerator bean name
   *
   * @return
   */
  String idGenerator() default "";

  /**
   * BizLoggerMessageGenerator bean name
   *
   * @return
   */
  String messageGenerator() default "";

  /**
   * BizLoggerHandler bean name
   *
   * @return
   */
  String handler() default "";

  /**
   * 是否记录入参数出参数
   *
   * @return
   */
  boolean logArgs() default false;

  /**
   * 是否记录抛异常的业务操作
   *
   * @return
   */
  boolean logFailed() default false;
}
