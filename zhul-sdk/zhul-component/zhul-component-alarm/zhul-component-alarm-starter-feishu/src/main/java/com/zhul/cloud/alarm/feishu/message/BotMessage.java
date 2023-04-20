package com.zhul.cloud.alarm.feishu.message;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.zhul.cloud.common.enums.AlarmLevel;
import com.zhul.cloud.common.enums.AlarmType;
import java.util.Date;
import lombok.Data;

/**
 * @Auther: yanglikai
 * @Date: 2020/9/22 09:03
 * @Description: 消息卡片
 */
@Data
public class BotMessage {

  private AlarmType type;

  private String appName;

  private String content;

  private AlarmLevel level;

  private String id;

  private String target;

  private String env;

  private String datetime;

  private String url;

  public BotMessage(String appName, AlarmType type, AlarmLevel level, String content, String env) {
    this(appName, type, level, content, env, "", "");
  }

  public BotMessage(String appName, AlarmType type, AlarmLevel level, String content, String env,
      String id,
      String target) {
    this.type = type;
    this.appName = appName;
    this.content = content;
    this.level = level;
    this.id = id;
    this.target = target;
    this.env = env;
    this.datetime = DateUtil.format(new Date(), DatePattern.NORM_DATETIME_PATTERN);
    this.url = getEnvUrl();
  }

  private String getEnvUrl() {
    return "";
  }
}
