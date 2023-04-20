package com.zhul.cloud.log.spring.boot;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 业务日志配置
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/9/25
 */
@Configuration
@ConditionalOnProperty(value = "zhul.cloud.log.enabled")
@AutoConfigureAfter(value = {LoggerAutoConfiguration.class})
public class BizLoggerAspectAutoConfiguration {

  @Bean
  public BizLoggerAspect bizLoggerAspect() {
    return new BizLoggerAspect();
  }

  @Bean
  public BizLoggerAspectSupport bizLoggerAspectSupport() {
    return new BizLoggerAspectSupport();
  }

}
