package com.zhul.cloud.common.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.zhul.cloud.common.exception.BizException;
import java.io.Serializable;

import com.zhul.cloud.common.enums.ErrorCode;
import lombok.Data;

/**
 * Created by yanglikai on 2019/5/28.
 */
@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Wrapper<T> implements Serializable {


  private String code;
  private String message;
  private T data;

  public Wrapper() {
    this(ErrorCode.SUCCESS.code(), ErrorCode.SUCCESS.msg());
  }

  public Wrapper(String code, String message) {
    this(code, message, null);
  }

  public Wrapper(String code, String message, T data) {
    super();
    this.code(code).message(message).data(data);
  }

  private Wrapper<T> code(String code) {
    this.setCode(code);
    return this;
  }

  private Wrapper<T> message(String message) {
    this.setMessage(message);
    return this;
  }

  public Wrapper<T> data(T data) {
    this.setData(data);
    return this;
  }

  @JsonIgnore
  public boolean success() {
    return ErrorCode.SUCCESS.code().equals(this.code);
  }

  @JsonIgnore
  public boolean error() {
    return !success();
  }

  public void check() {
    if (error()) {
      if (ErrorCode.FAIL.code().equals(this.code)) {
        throw new BizException(ErrorCode.FAIL);
      } else {
        throw new BizException(this.code, this.message);
      }
    }
  }

  public Wrapper<T> checkAndReturn() {
    check();
    return this;
  }

  public T checkAndGet() {
    check();
    if (this.getData() == null) {
      throw new BizException(ErrorCode.A0404);
    }
    return this.getData();
  }
}
