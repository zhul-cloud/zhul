package com.zhul.cloud.alarm.feishu.exception;

/**
 * Created by yanglikai on 2022/12/01.
 */
public class ApiException extends RuntimeException {

  public ApiException() {
  }

  public ApiException(String msg) {
    super(msg);
  }

  public ApiException(String message, Throwable cause) {
    super(message, cause);
  }

  public ApiException(Throwable cause) {
    super(cause);
  }
}
