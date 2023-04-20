package com.zhul.cloud.examples.stream.binding;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 订单创建-消费者
 * Created by yanglikai on 2021/1/5.
 */
public interface OrderCreatedInput {

  /**
   * Input channel name.
   */
  String INPUT = "order-created-input";

  /**
   * @return input channel.
   */
  @Input(OrderCreatedInput.INPUT)
  SubscribableChannel input();
}
