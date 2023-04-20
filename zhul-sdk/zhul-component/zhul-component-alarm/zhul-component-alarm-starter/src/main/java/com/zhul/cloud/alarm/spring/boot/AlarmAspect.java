package com.zhul.cloud.alarm.spring.boot;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by yanglikai on 2021/9/8.
 */
@Slf4j
@Aspect
public class AlarmAspect {

  @Autowired(required = false)
  private AlarmCollector alarmCollector;

  @Around(value = "@annotation(responseStatus)")
  public Object responseStatus(ProceedingJoinPoint joinPoint, ResponseStatus responseStatus)
      throws Throwable {

    Object object = joinPoint.proceed();

    try {
      String log = resolve(joinPoint, responseStatus);

      /* 系统异常 */
      if (HttpStatus.INTERNAL_SERVER_ERROR == responseStatus.value()) {
        alarmCollector.exceptionCollector(log);
      }

      /* 业务异常 */
      if (HttpStatus.OK == responseStatus.value()) {
        alarmCollector.bizCollector(log);
      }
    } catch (Exception ex) {
    }

    return object;
  }

  private String resolve(ProceedingJoinPoint joinPoint, ResponseStatus responseStatus) {

    try {
      Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
      return getArgs(joinPoint.getArgs(), method);
    } catch (Exception ex) {
      log.error("responseStatus resolve failed.");
    }

    return "";
  }


  private String getArgs(Object[] args, Method method) throws Exception {
    Class<?>[] methodParameterTypes = method.getParameterTypes();

    if (args.length != methodParameterTypes.length) {
      throw new Exception("method parameter size != arg size.");
    }

    if (args.length > 0) {
      for (int i = 0; i < args.length; i++) {
        Object argValue = args[i];
        if (argValue instanceof Exception) {
          Exception exception = (Exception) argValue;

          return exception.toString();
        }
      }
    }

    return "";
  }
}
