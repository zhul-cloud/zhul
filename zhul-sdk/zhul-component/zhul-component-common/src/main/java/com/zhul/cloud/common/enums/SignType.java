package com.zhul.cloud.common.enums;

/**
 * 签名类型
 * <p></p>
 * Created by yanglikai on 2021/9/1.
 */
public enum SignType {

  HMACSHA256("HMAC-SHA256"),

  MD5("MD5"),

  RSA("RSA");

  SignType(String type) {
    this.type = type;
  }

  private final String type;

  public String getType() {
    return type;
  }

  @Override
  public String toString() {
    return type;
  }
}
