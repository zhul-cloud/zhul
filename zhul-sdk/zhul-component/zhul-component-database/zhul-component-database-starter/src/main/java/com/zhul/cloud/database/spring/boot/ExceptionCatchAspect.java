package com.zhul.cloud.database.spring.boot;

import cn.hutool.json.JSONUtil;
import com.zhul.cloud.common.annotation.ExceptionCatch;
import com.zhul.cloud.common.client.ExceptionStorage;
import com.zhul.cloud.common.client.ExceptionStorageService;
import com.zhul.cloud.common.stream.evnet.Event;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yanglikai on 2021/8/30.
 */
@Slf4j
@Aspect
public class ExceptionCatchAspect {

  @Autowired(required = false)
  private ExceptionStorageService service;

  @Around(value = "@annotation(exceptionCatch)")
  public Object exceptionCatch(ProceedingJoinPoint joinPoint, ExceptionCatch exceptionCatch)
      throws Throwable {

    try {
      return joinPoint.proceed();
    } catch (Exception ex) {
      if (service != null) {
        ExceptionStorage exceptionStorage = resolve(joinPoint, exceptionCatch, ex);

        if (exceptionStorage != null) {
          service.storage(exceptionStorage);
        }
      }

      ex.printStackTrace();
    }

    return null;
  }

  private ExceptionStorage resolve(ProceedingJoinPoint joinPoint, ExceptionCatch exceptionCatch,
      Exception exception) {

    try {
      Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
      String args = getArgs(joinPoint.getArgs(), method);
      if (StringUtils.isBlank(args)) {
        return null;
      }

      ExceptionStorage exceptionStorage = new ExceptionStorage();
      exceptionStorage.setRequest(args);
      exceptionStorage.setSuccessFlag(exception != null ? false : true);
      exceptionStorage.setException(getException(exception));

      return exceptionStorage;
    } catch (Exception ex) {
      log.error("exceptionCatch resolve failed.");
    }

    return null;
  }


  private String getArgs(Object[] args, Method method) throws Exception {
    Class<?>[] methodParameterTypes = method.getParameterTypes();

    if (args.length != methodParameterTypes.length) {
      throw new Exception("method parameter size != arg size.");
    }

    if (args.length > 0) {
      for (int i = 0; i < args.length; i++) {
        Object argValue = args[i];
        return JSONUtil.toJsonStr(argValue);
      }
    }

    return "";
  }

  private String getException(Exception ex) {
    StringWriter sw = new StringWriter();

    ex.printStackTrace(new PrintWriter(sw, true));

    return sw.getBuffer().toString();
  }
}
