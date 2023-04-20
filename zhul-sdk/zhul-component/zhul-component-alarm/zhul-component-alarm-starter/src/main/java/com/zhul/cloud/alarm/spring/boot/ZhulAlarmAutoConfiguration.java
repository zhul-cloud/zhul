package com.zhul.cloud.alarm.spring.boot;

import com.zhul.cloud.alarm.feishu.FeishuAlarm;
import com.zhul.cloud.alarm.feishu.FeishuAlarmAutoConfiguration;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by yanglikai on 2021/9/7.
 */
@Configuration
@ConditionalOnProperty(value = "zhul.cloud.alarm.enabled")
@Import({FeishuAlarmAutoConfiguration.class})
public class ZhulAlarmAutoConfiguration {

  @Autowired
  private FeishuAlarm feishuAlarm;

  @Value("${zhul.cloud.alarm.level:ERROR}")
  private Set<String> alarmLevel;

  @Bean
  public AlarmCollector alarmCollector() {
    return new AlarmCollector(this.feishuAlarm, this.alarmLevel);
  }
}
