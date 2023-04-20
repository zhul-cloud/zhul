package com.zhul.cloud.log.spring.boot;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.zhul.cloud.common.logger.BizLogger;
import com.zhul.cloud.common.logger.BizLoggerContextHolder;
import com.zhul.cloud.common.logger.BizLoggerIdGenerator;
import com.zhul.cloud.common.logger.BizLoggerHandler;
import com.zhul.cloud.common.logger.BizLoggerMessage;
import com.zhul.cloud.common.logger.BizLoggerMessageGenerator;
import com.zhul.cloud.common.logger.BizLoggerMetadata;
import java.lang.reflect.Method;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.StringUtils;
import org.springframework.util.function.SingletonSupplier;

/**
 * aop工具
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/9/29
 */
@Slf4j
public class BizLoggerAspectSupport implements BeanFactoryAware {

  private SingletonSupplier<BizLoggerIdGenerator> defaultBizLoggerIdGenerator = SingletonSupplier
      .of(
          DefaultBizLoggerIdGenerator::new);
  private SingletonSupplier<BizLoggerMessageGenerator> defaultBizLoggerMessageGenerator = SingletonSupplier
      .of(DefaultBizLoggerMessageGenerator::new);
  private SingletonSupplier<BizLoggerHandler> defaultBizLoggerHandler = SingletonSupplier.of(
      DefaultBizLoggerHandler::new);

  private final static Map<Method, BizLoggerMetadata> METADATA_CACHE = Maps.newConcurrentMap();
  @Nullable
  private BeanFactory beanFactory;


  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    this.beanFactory = beanFactory;
  }

  @Async("zhulCloudLogAsyncExecutor")
  public void execute(BizLoggerMessage message, BizLogger annotation,
      Class targetClass, Method targetMethod, Object[] reqParams, Object respParams,
      Map<String, Object> context) {

    BizLoggerMetadata metadata = METADATA_CACHE.get(targetMethod);
    if (metadata == null) {
      BizLoggerIdGenerator bizLoggerIdGenerator = getBizLoggerIdGenerator(annotation.idGenerator());
      BizLoggerMessageGenerator bizLoggerMessageGenerator = getBizLoggerMessageGenerator(
          annotation.messageGenerator());
      BizLoggerHandler bizLoggerHandler = getBizLoggerHandler(annotation.handler());

      metadata = new BizLoggerMetadata(targetMethod, targetClass, bizLoggerIdGenerator,
          bizLoggerMessageGenerator, bizLoggerHandler, annotation);
      METADATA_CACHE.put(targetMethod, metadata);
    }

    BizLoggerContextHolder contextHolder = new BizLoggerContextHolder(metadata, reqParams,
        respParams, context);
    String bizId = contextHolder.generateBizId();
    // 唯一标志
    message.setBizId(bizId);
    // 业务模块编码
    message.setBizCode(annotation.bizCode());
    // 业务模块名称
    message.setBizName(annotation.bizName());
    // 操作类型编码
    message.setOptCode(annotation.optCode());
    // 操作类型名称
    message.setOptName(annotation.optName());
    // 类名方法名
    message.setMethod(targetMethod.toString());
    if (annotation.logArgs()) {
      try {
        // 入参
        message.setReqParams(JSON.toJSONString(reqParams));
      } catch (Exception e) {
        log.error(e.toString(), e);
      }
      try {
        // 出参
        message.setRespParams(JSON.toJSONString(respParams));
      } catch (Exception e) {
        log.error(e.toString(), e);
      }
    }

    // 生成日志消息
    Object finalMessage = contextHolder.generateMessage(message);
    // 持久化日志消息
    contextHolder.persistent(finalMessage);
    BizLoggerContext.removeContexts();
  }

  private BizLoggerIdGenerator getBizLoggerIdGenerator(String generator) {
    BizLoggerIdGenerator bizLoggerIdGenerator = null;

    if (StringUtils.hasText(generator)) {
      bizLoggerIdGenerator = (BizLoggerIdGenerator) beanFactory.getBean(generator);
    }
    if (bizLoggerIdGenerator == null) {
      bizLoggerIdGenerator = defaultBizLoggerIdGenerator.obtain();
    }
    return bizLoggerIdGenerator;
  }


  private BizLoggerMessageGenerator getBizLoggerMessageGenerator(String generator) {
    BizLoggerMessageGenerator bizLoggerMessageGenerator = null;

    if (StringUtils.hasText(generator)) {
      bizLoggerMessageGenerator = (BizLoggerMessageGenerator) beanFactory.getBean(generator);
    }
    if (bizLoggerMessageGenerator == null) {
      bizLoggerMessageGenerator = defaultBizLoggerMessageGenerator.obtain();
    }
    return bizLoggerMessageGenerator;
  }

  private BizLoggerHandler getBizLoggerHandler(String handler) {
    BizLoggerHandler bizLoggerHandler = null;
    if (StringUtils.hasText(handler)) {
      bizLoggerHandler = (BizLoggerHandler) beanFactory.getBean(handler);
    }
    if (bizLoggerHandler == null) {
      try {
        bizLoggerHandler = beanFactory.getBean(BizLoggerHandler.class);
      } catch (Exception e) {
        log.warn("beanFactory.getBean(BizLoggerHandler.class)失败");
      }
    }
    if (bizLoggerHandler == null) {
      bizLoggerHandler = defaultBizLoggerHandler.obtain();
    }
    return bizLoggerHandler;
  }

}
