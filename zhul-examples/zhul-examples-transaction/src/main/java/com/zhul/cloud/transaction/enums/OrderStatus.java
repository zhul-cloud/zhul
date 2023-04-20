package com.zhul.cloud.transaction.enums;

/**
 * Created by yanglikai on 2021/1/15.
 */
public enum OrderStatus {

  DEFAULT(0, "默认"),

  PAYING(1, "待支付"),

  PAY_UPDATING(2, "支付待更新"),

  PAYED(3, "已支付"),
  ;

  public int code;
  public String desc;

  OrderStatus(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }
}
