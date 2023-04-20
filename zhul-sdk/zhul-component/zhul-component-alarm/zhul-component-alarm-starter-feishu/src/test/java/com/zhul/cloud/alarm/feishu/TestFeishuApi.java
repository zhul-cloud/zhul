package com.zhul.cloud.alarm.feishu;

import org.junit.Test;

/**
 * Created by yanglikai on 2022/12/1.
 */
public class TestFeishuApi {

  @Test
  public void test_ok() {
    String token = "8b2ed8dc-8980-4cec-bfbd-f8c38a6fb9b6";
    String applicationName = "zhul-component-alarm";

    FeishuAlarm alarm = new FeishuAlarm(token, applicationName);
    alarm.alarm("烛龙-告警-报错");
  }

  @Test
  public void test_error() {
    String token = "sdfsdf";
    String applicationName = "zhul-component-alarm";

    FeishuAlarm alarm = new FeishuAlarm(token, applicationName);
    alarm.alarm("烛龙-告警-报错");
  }
}
