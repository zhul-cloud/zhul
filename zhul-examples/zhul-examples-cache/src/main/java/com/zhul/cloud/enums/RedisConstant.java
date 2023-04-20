package com.zhul.cloud.enums;

/**
 * Created by yanglikai on 2020/12/29.
 */
public enum RedisConstant {

  USER_NAME("zhul:examples:user:name:%s", "用户名", 60 * 1), // 一分钟

  BY_USER("zhul:examples:user:%s", "用户信息", 60 * 60), // 一小时
  ;

  public final String key;

  public final String desc;

  public final long expired;

  RedisConstant(String key, String desc, long expired) {
    this.key = key;
    this.desc = desc;
    this.expired =expired;
  }
}
