package com.zhul.cloud.log.spring.boot;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Data;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.context.expression.CachedExpressionEvaluator;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;

/**
 * 语法解析器
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/9/29
 */
public class BizLoggerExpressionEvaluator extends CachedExpressionEvaluator {

  private final Map<ExpressionKey, Expression> keyCache = new ConcurrentHashMap<>(64);

  public Object key(String keyExpression, AnnotatedElementKey methodKey,
      EvaluationContext evalContext) {
    return getExpression(this.keyCache, methodKey, keyExpression).getValue(evalContext);
  }

  public EvaluationContext createEvaluationContext(Class<?> targetClass,
      Method targetMethod, Object[] reqParams, Object respParams) {
    ExpressionRootObject rootObject = new ExpressionRootObject(targetClass, targetMethod, reqParams,
        respParams);

    MethodBasedEvaluationContext evaluationContext = new MethodBasedEvaluationContext(
        rootObject, targetMethod, reqParams, getParameterNameDiscoverer());
    return evaluationContext;
  }

  @Data
  public static class ExpressionRootObject {

    private final Object object;
    private final Method method;
    private final Object[] reqParams;
    private final Object respParams;

    public ExpressionRootObject(Object object, Method method, Object[] reqParams,
        Object respParams) {
      this.object = object;
      this.method = method;
      this.reqParams = reqParams;
      this.respParams = respParams;
    }

  }
}
