package com.zhul.cloud.common.exception;


import com.zhul.cloud.common.enums.ErrorCode;

/**
 * Created by yanglikai on 2019/5/28.
 */
public class BizException extends RuntimeException {
  private static final long serialVersionUID = 746403296908484046L;

  private String code;

  public BizException() {
  }

  public BizException(String message) {
    super(message);
  }

  public BizException(Throwable cause) {
    super(cause);
  }

  public BizException(String message, Throwable cause) {
    super(message, cause);
  }

  public BizException(String code, String message) {
    super(message);
    this.code = code;
  }

  public BizException(String code, String messageFormat, Object... args) {
    super(String.format(messageFormat, args));
    this.code = code;
  }

  public BizException(ErrorCode codeEnum, Object... args) {
    super(String.format(codeEnum.msg(), args));
    this.code = codeEnum.code();
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
