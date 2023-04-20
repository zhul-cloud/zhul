package com.zhul.cloud.database.spring.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yanglikai on 2021/8/30.
 */
@Configuration
public class ExceptionCatchAspectAutoConfiguration {

  @Bean
  public ExceptionCatchAspect exceptionCatchAspect() {
    return new ExceptionCatchAspect();
  }
}
