package com.zhul.cloud.database.sharding;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yanglikai on 2021/8/6.
 */
@Configuration
@ConditionalOnProperty(value = "zhul.cloud.database.enabled", matchIfMissing = true)
public class MasterSelectAspectAutoConfiguration {

  @Bean
  public MasterSelectAspect masterSelectAspect() {
    return new MasterSelectAspect();
  }
}
