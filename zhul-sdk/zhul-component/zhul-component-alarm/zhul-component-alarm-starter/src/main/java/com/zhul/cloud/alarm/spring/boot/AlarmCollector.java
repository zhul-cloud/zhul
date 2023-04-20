package com.zhul.cloud.alarm.spring.boot;

import com.zhul.cloud.alarm.feishu.FeishuAlarm;
import com.zhul.cloud.common.enums.AlarmLevel;
import java.util.Set;

/**
 * Created by yanglikai on 2021/9/8.
 */
public class AlarmCollector {

  private FeishuAlarm feishuAlarm;

  private Set<String> alarmLevel;

  public AlarmCollector(FeishuAlarm feishuAlarm, Set<String> alarmLevel) {
    this.feishuAlarm = feishuAlarm;
    this.alarmLevel = alarmLevel;
  }

  public void collector(String log) {
    this.feishuAlarm.alarm(log);
  }

  public void bizCollector(String log) {
    if (!this.alarmLevel.contains(AlarmLevel.WARNING.getLevel())) {
      return;
    }

    this.feishuAlarm.bizAlarm(log);
  }

  public void exceptionCollector(String log) {
    if (!this.alarmLevel.contains(AlarmLevel.ERROR.getLevel())) {
      return;
    }

    this.feishuAlarm.exceptionAlarm(log);
  }
}
