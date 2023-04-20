package com.zhul.cloud.common.enums;

/**
 * 告警类型
 * <p></p>
 * Created by yanglikai on 2021/9/17.
 */
public enum AlarmType {

  DEFAULT("Default", "默认"),

  BIZ("Business", "业务告警"),

  EXCEPTION("Exception", "异常告警");

  private String name;

  private String title;

  private AlarmType(String name, String title) {
    this.name = name;
    this.title = title;
  }

  public static AlarmType getTypeByName(String name) {
    for (AlarmType type : AlarmType.values()) {
      if (type.getName().equals(name)) {
        return type;
      }
    }

    return AlarmType.DEFAULT;
  }

  public String getName() {
    return name;
  }

  public String getTitle() {
    return title;
  }
}
