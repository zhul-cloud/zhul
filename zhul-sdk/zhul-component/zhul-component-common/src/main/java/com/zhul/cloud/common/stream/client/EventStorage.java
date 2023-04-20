package com.zhul.cloud.common.stream.client;

import com.zhul.cloud.common.stream.evnet.Event;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * 事件存储
 * <p></p>
 * Created by yanglikai on 2021/8/6.
 */
@Data
public class EventStorage implements Serializable {

  @ApiModelProperty(value = "消息事件")
  private Event event;

  @ApiModelProperty(value = "消费标识", notes = "true-消费成功、false-消费失败")
  private boolean consumeFlag;

  @ApiModelProperty(value = "异常信息")
  private String exception;
}
