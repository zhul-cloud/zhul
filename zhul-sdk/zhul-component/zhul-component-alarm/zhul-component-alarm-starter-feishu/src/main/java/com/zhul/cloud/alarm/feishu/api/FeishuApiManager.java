package com.zhul.cloud.alarm.feishu.api;


import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.zhul.cloud.alarm.feishu.model.FeishuBotAlertParams;
import com.zhul.cloud.alarm.feishu.model.FeishuBotAlertResult;
import com.zhul.cloud.alarm.feishu.model.FeishuProperties;

/**
 * Created by yanglikai on 2020/9/17.
 */
public final class FeishuApiManager {

  private FeishuProperties feishu;

  private FeishuApiManager() {
  }

  public static FeishuApiManager init() {
    return new FeishuApiManager();
  }

  public FeishuApiManager setup(FeishuProperties properties) {
    this.feishu = properties;
    return this;
  }

  public FeishuBotAlertResult request(FeishuBotAlertParams params) {
    String response = HttpRequest
        .post("https://open.feishu.cn/open-apis/bot/v2/hook/" + this.feishu.getToken())
        .body(JSONUtil.toJsonStr(params)).execute().body();

    return JSONUtil.toBean(response, FeishuBotAlertResult.class);
  }
}
