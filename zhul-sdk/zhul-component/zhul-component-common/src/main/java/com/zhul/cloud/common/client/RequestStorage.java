package com.zhul.cloud.common.client;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * 请求存储
 * <p></p>
 * Created by yanglikai on 2021/9/03.
 */
@Data
public class RequestStorage implements Serializable {

  @ApiModelProperty(value = "请求源")
  private String source;

  @ApiModelProperty(value = "请求参数")
  private String request;

  @ApiModelProperty(value = "响应结果")
  private String response;
}
