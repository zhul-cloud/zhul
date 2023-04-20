package com.zhul.cloud.common.logger;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import lombok.Getter;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.core.BridgeMethodResolver;

/**
 * 业务日志信息
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/9/25
 */
@Getter
public class BizLoggerMetadata {

  private final BizLogger annotation;

  private final Method method;

  private final Class<?> targetClass;

  private final Method targetMethod;

  private final AnnotatedElementKey methodKey;

  private final BizLoggerIdGenerator bizLoggerIdGenerator;

  private final BizLoggerMessageGenerator bizLoggerMessageGenerator;

  private final BizLoggerHandler bizLoggerHandler;

  public BizLoggerMetadata(Method method, Class<?> targetClass,
      BizLoggerIdGenerator bizLoggerIdGenerator,
      BizLoggerMessageGenerator bizLoggerMessageGenerator,
      BizLoggerHandler bizLoggerHandler, BizLogger annotation) {
    this.method = BridgeMethodResolver.findBridgedMethod(method);
    this.targetClass = targetClass;
    this.targetMethod = (!Proxy.isProxyClass(targetClass) ? AopUtils
        .getMostSpecificMethod(method, targetClass) : this.method);
    this.methodKey = new AnnotatedElementKey(this.targetMethod, targetClass);
    this.bizLoggerIdGenerator = bizLoggerIdGenerator;
    this.bizLoggerMessageGenerator = bizLoggerMessageGenerator;
    this.bizLoggerHandler = bizLoggerHandler;
    this.annotation = annotation;
  }
}