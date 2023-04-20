package com.zhul.cloud.common.enums;

/**
 * 告警级别
 * <P></P>
 * Created by yanglikai on 2021/9/17.
 */
public enum AlarmLevel {
  WARNING("WARNING", 1),

  ERROR("ERROR", 2);

  private String level;

  private int priority;

  private AlarmLevel(String level, int priority) {
    this.level = level;
    this.priority = priority;
  }

  public static AlarmLevel findByName(String level) {
    for (AlarmLevel tmp : values()) {
      if (tmp.getLevel().equals(level)) {
        return tmp;
      }
    }
    return WARNING;
  }

  public String getLevel() {
    return level;
  }

  public int getPriority() {
    return priority;
  }
}
