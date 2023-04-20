package com.zhul.cloud.database.sharding;

import com.zhul.cloud.common.annotation.MasterSelect;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * Created by yanglikai on 2021/8/6.
 */
@Slf4j
@Aspect
@ConditionalOnProperty(value = "zhul.cloud.database.enabled", matchIfMissing = true)
public class MasterSelectAspect {

  @Around(value = "@annotation(masterSelect)")
  public Object setMasterSelect(ProceedingJoinPoint joinPoint, MasterSelect masterSelect)
      throws Throwable {
    Object object = null;

    Throwable currentThrowable = null;

    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

    if (methodSignature.getMethod().isAnnotationPresent(MasterSelect.class)) {
      if (!HintManager.isDatabaseShardingOnly()) {
        HintManager.getInstance().setWriteRouteOnly();

        if (log.isDebugEnabled()) {
          log.info("MasterSelect Hit!");
        }
      }
    }

    try {
      object = joinPoint.proceed();
    } catch (Throwable throwable) {
      currentThrowable = throwable;
    } finally {
      HintManager.clear();
      if (currentThrowable != null) {
        throw currentThrowable;
      }
    }

    return object;
  }
}
