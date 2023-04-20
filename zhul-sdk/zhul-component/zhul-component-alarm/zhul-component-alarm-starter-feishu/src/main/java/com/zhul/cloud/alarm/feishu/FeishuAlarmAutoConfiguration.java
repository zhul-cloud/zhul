package com.zhul.cloud.alarm.feishu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yanglikai on 2021/9/7.
 */
@Configuration
@ConditionalOnProperty(value = "zhul.cloud.alarm.enabled")
public class FeishuAlarmAutoConfiguration {

  @Value("${zhul.cloud.alarm.feishu.token}")
  private String token;

  @Value("${spring.application.name:}")
  private String applicationName;

  @Bean
  public FeishuAlarm feishuAlarm() {
    return new FeishuAlarm(token, applicationName);
  }
}
