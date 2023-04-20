package com.zhul.cloud.stream.spring.boot;

import cn.hutool.json.JSONUtil;
import com.zhul.cloud.common.stream.client.EventStorage;
import com.zhul.cloud.common.stream.client.EventStorageService;
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
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * Created by yanglikai on 2021/8/6.
 */
@Slf4j
@Aspect
public class StreamListenerAspect {

  @Autowired(required = false)
  private EventStorageService service;

  @Around(value = "@annotation(streamListener)")
  public Object streamListener(ProceedingJoinPoint joinPoint, StreamListener streamListener)
      throws Throwable {

    try {
      return joinPoint.proceed();
    } catch (Exception ex) {
      if (service != null) {
        EventStorage eventStorage = resolve(joinPoint, streamListener, ex);

        if (eventStorage != null) {
          service.storage(eventStorage);
        }
        ex.printStackTrace();
      } else {
        throw ex;
      }

    }

    return null;
  }

  private EventStorage resolve(ProceedingJoinPoint joinPoint, StreamListener streamListener,
      Exception exception) {

    try {
      Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
      String args = getArgs(joinPoint.getArgs(), method);
      if (StringUtils.isBlank(args)) {
        return null;
      }

      EventStorage eventStorage = new EventStorage();
      eventStorage.setEvent(JSONUtil.toBean(args, Event.class));
      eventStorage.setConsumeFlag(exception != null ? false : true);
      eventStorage.setException(getException(exception));

      return eventStorage;
    } catch (Exception ex) {
      log.error("streamListener resolve failed.");
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
        if (argValue instanceof Event) {
          return JSONUtil.toJsonStr(argValue);
        }
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
