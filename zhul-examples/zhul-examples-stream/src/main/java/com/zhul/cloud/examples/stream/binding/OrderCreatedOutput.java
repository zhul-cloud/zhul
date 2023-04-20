package com.zhul.cloud.examples.stream.binding;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 订单创建-生产者
 * Created by yanglikai on 2021/1/5.
 */
public interface OrderCreatedOutput {

  /**
   * Name of the output channel.
   */
  String OUTPUT = "order-created-output";

  /**
   * @return output channel
   */
  @Output(OrderCreatedOutput.OUTPUT)
  MessageChannel output();
}
