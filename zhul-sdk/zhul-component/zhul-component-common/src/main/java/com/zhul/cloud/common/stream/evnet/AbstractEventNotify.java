package com.zhul.cloud.common.stream.evnet;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;

/**
 * Created by yanglikai on 2021/5/28.
 */
public abstract class AbstractEventNotify implements EventNotify<Event> {

  private MessageChannel output;

  /**
   * 设置输出通道
   * <p></p>
   *
   * @param output 输出通道对象
   */
  public void setOutput(MessageChannel output) {
    this.output = output;
  }

  /**
   * 事件发送
   * <p></p>
   *
   * @param event 事件对象
   */
  @Async
  @Override
  public void send(Event event) {
    Objects.requireNonNull(this.output, "output must be not null");

    Map<String, Object> headers = new HashMap<>();
    headers.put(MessageConst.PROPERTY_TAGS, event.getTags());
    headers.put(MessageConst.PROPERTY_KEYS, event.getKey());

    Message<Event> message = MessageBuilder
        .createMessage(event, new MessageHeaders(headers));

    this.output.send(message);
  }
}
