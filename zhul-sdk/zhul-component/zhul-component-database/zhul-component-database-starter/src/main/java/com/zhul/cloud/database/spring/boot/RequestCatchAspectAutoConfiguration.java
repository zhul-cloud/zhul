package com.zhul.cloud.database.spring.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yanglikai on 2021/9/03.
 */
@Configuration
public class RequestCatchAspectAutoConfiguration {

  @Bean
  public RequestCatchAspect requestCatchAspect() {
    return new RequestCatchAspect();
  }
}
