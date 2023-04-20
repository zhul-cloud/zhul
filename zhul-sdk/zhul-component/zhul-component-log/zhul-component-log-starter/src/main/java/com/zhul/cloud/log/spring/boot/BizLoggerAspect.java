package com.zhul.cloud.log.spring.boot;

import cn.hutool.core.date.DateTime;
import com.zhul.cloud.common.logger.BizLogger;
import com.zhul.cloud.common.logger.BizLoggerMessage;
import com.zhul.cloud.common.logger.OptUserHandler;
import com.zhul.cloud.common.logger.OptUser;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


/**
 * 业务日志aop
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/9/25
 */
@Slf4j
@Aspect
public class BizLoggerAspect {

  @Value("${spring.application.name:}")
  private String applicationName;
  @Autowired
  private BizLoggerAspectSupport bizLoggerAspectSupport;
  @Autowired(required = false)
  private OptUserHandler optUserHandler;

  @Around(value = "@annotation(bizLogger)")
  public Object logBiz(ProceedingJoinPoint joinPoint, BizLogger bizLogger) throws Throwable {
    Object object = null;
    Object target = joinPoint.getTarget();
    Object[] args = joinPoint.getArgs();
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

    Date now = DateTime.now();
    BizLoggerMessage bizLoggerMessage = new BizLoggerMessage();
    // 应用名
    bizLoggerMessage.setApplicationName(applicationName);
    // 日志时间
    bizLoggerMessage.setOptTime(now);
    // 操作人
    if (optUserHandler != null) {
      OptUser optUser = optUserHandler.generate(args);
      if (optUser != null) {
        bizLoggerMessage.setOptUserId(optUser.getOptUserId());
        bizLoggerMessage.setOptUserName(optUser.getOptUserName());
      }
    }
    try {
      object = joinPoint.proceed();
      // 操作结果 0失败1成功
      bizLoggerMessage.setResult(1);
    } catch (Throwable throwable) {
      bizLoggerMessage.setResult(0);
      bizLoggerMessage.setException(throwable.toString());
      throw throwable;
    } finally {
      // 是否记录抛异常的业务操作
      if (bizLoggerMessage.getResult() == 1 || bizLogger.logFailed()) {
        bizLoggerAspectSupport.execute(bizLoggerMessage, bizLogger, target.getClass(),
            methodSignature.getMethod(), args, object, BizLoggerContext.getContexts());
      }
    }
    return object;
  }

}
