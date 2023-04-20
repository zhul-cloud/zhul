package com.zhul.cloud.database.spring.boot;

import cn.hutool.json.JSONUtil;
import com.zhul.cloud.common.annotation.RequestCatch;
import com.zhul.cloud.common.client.RequestStorage;
import com.zhul.cloud.common.client.RequestStorageService;
import com.zhul.cloud.common.model.CatchArgs;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yanglikai on 2021/9/03.
 */
@Slf4j
@Aspect
public class RequestCatchAspect {

  @Autowired(required = false)
  private RequestStorageService service;

  @Around(value = "@annotation(requestCatch)")
  public Object requestCatch(ProceedingJoinPoint joinPoint, RequestCatch requestCatch)
      throws Throwable {

    if (service != null) {
      RequestStorage requestStorage = resolve(joinPoint, requestCatch);

      if (requestStorage != null) {
        service.storage(requestStorage);
      }
    }

    Object object = joinPoint.proceed();

    return object;
  }

  private RequestStorage resolve(ProceedingJoinPoint joinPoint, RequestCatch requestCatch) {

    try {
      Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
      String args = getArgs(joinPoint.getArgs(), method);
      if (StringUtils.isBlank(args)) {
        return null;
      }

      RequestStorage requestStorage = new RequestStorage();
      requestStorage.setSource(requestCatch.source());
      requestStorage.setRequest(args);

      return requestStorage;
    } catch (Exception ex) {
      log.error("requestCatch resolve failed.");
    }

    return null;
  }


  private String getArgs(Object[] args, Method method) throws Exception {
    Class<?>[] methodParameterTypes = method.getParameterTypes();

    if (args.length != methodParameterTypes.length) {
      throw new Exception("method parameter size != arg size.");
    }

    CatchArgs catchArgs = new CatchArgs();

    if (args.length > 0) {
      for (int i = 0; i < args.length; i++) {
        String type = methodParameterTypes[i].getName();
        Object argValue = args[i];

        catchArgs.add(type, argValue);
      }
    }

    return JSONUtil.toJsonStr(catchArgs);
  }
}
