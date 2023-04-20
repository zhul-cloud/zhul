package com.zhul.cloud.stream.spring.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yanglikai on 2021/8/6.
 */
@Configuration
public class StreamListenerAspectAutoConfiguration {

  @Bean
  public StreamListenerAspect streamListenerAspect() {
    return new StreamListenerAspect();
  }
}
