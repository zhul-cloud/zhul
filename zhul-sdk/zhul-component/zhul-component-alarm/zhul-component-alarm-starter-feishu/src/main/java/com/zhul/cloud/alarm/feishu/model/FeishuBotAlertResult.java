package com.zhul.cloud.alarm.feishu.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Auther: yanglikai
 * @Date: 2020/9/21 20:12
 * @Description:
 */
@Data
public class FeishuBotAlertResult {

  @JsonProperty(value = "Extra")
  private String extra;

  @JsonProperty(value = "StatusCode")
  private Integer StatusCode;

  @JsonProperty(value = "StatusMessage")
  private String StatusMessage;

  @JsonProperty(value = "code")
  private String code;

  @JsonProperty(value = "msg")
  private String msg;

  public boolean isOK() {
    return Integer.valueOf(0).equals(this.getStatusCode());
  }

  public boolean hasError() {
    return !isOK();
  }
}
