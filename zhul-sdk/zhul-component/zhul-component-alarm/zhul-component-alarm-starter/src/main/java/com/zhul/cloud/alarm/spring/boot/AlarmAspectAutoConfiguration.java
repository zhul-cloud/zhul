package com.zhul.cloud.alarm.spring.boot;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yanglikai on 2021/9/8.
 */
@Configuration
@ConditionalOnProperty(value = "zhul.cloud.alarm.enabled")
@AutoConfigureAfter(value = {ZhulAlarmAutoConfiguration.class})
public class AlarmAspectAutoConfiguration {

  @Bean
  public AlarmAspect alarmAspect() {
    return new AlarmAspect();
  }
}
