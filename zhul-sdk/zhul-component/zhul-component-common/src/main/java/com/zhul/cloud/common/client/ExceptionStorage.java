package com.zhul.cloud.common.client;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * 异常存储
 * <p></p>
 * Created by yanglikai on 2021/8/30.
 */
@Data
public class ExceptionStorage implements Serializable {

  @ApiModelProperty(value = "请求参数")
  private String request;

  @ApiModelProperty(value = "成功标识", notes = "true-请求成功、false-请求失败")
  private boolean successFlag;

  @ApiModelProperty(value = "异常信息")
  private String exception;
}
