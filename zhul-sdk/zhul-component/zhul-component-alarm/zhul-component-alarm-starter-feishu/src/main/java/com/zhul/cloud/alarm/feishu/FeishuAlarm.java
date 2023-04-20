package com.zhul.cloud.alarm.feishu;

import com.zhul.cloud.alarm.feishu.api.FeishuApiManager;
import com.zhul.cloud.alarm.feishu.exception.ApiException;
import com.zhul.cloud.alarm.feishu.message.BotMessage;
import com.zhul.cloud.alarm.feishu.message.MessageWrapper;
import com.zhul.cloud.alarm.feishu.model.FeishuBotAlertParams;
import com.zhul.cloud.alarm.feishu.model.FeishuBotAlertResult;
import com.zhul.cloud.alarm.feishu.model.FeishuProperties;
import com.zhul.cloud.common.enums.AlarmLevel;
import com.zhul.cloud.common.enums.AlarmType;

/**
 * 飞书告警器
 * <p></p>
 * Created by yanglikai on 2021/9/7.
 */
public class FeishuAlarm {

  private String token;

  private String applicationName;

  private FeishuApiManager apiManager;

  public FeishuAlarm(String token, String applicationName) {
    this.token = token;
    this.applicationName = applicationName;

    init();
  }

  private void init() {
    FeishuProperties properties = new FeishuProperties();
    properties.setToken(this.token);

    this.apiManager = FeishuApiManager.init().setup(properties);
  }

  public boolean alarm(String content) {
    return this.alarm(AlarmType.EXCEPTION, AlarmLevel.ERROR, content);
  }

  public boolean bizAlarm(String content) {
    return this.alarm(AlarmType.BIZ, AlarmLevel.WARNING, content);
  }

  public boolean exceptionAlarm(String content) {
    return this.alarm(AlarmType.EXCEPTION, AlarmLevel.ERROR, content);
  }

  public boolean alarm(AlarmType type, AlarmLevel level, String content) {
    BotMessage message = new BotMessage(this.applicationName, type, level, content, obtainEnv());

    FeishuBotAlertParams params = MessageWrapper.wrap(message);

    FeishuBotAlertResult result = this.apiManager.request(params);
    if (result.hasError()) {
      throw new ApiException(result.getMsg());
    }

    return result.isOK();
  }

  private static final String ENV_TEST = "test";
  private static final String ENV_PRD = "prd";

  private String obtainEnv() {
    String current = System.getProperty("env");

    if (ENV_TEST.equals(current)) {
      return "测试环境";
    }

    if (ENV_PRD.equals(current)) {
      return "生产环境";
    }

    return "开发环境";
  }
}
