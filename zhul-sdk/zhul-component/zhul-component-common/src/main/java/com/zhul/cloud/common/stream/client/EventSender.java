package com.zhul.cloud.common.stream.client;

import com.zhul.cloud.common.stream.evnet.AbstractEventNotify;
import com.zhul.cloud.common.stream.evnet.Event;
import java.util.Objects;
import org.springframework.messaging.MessageChannel;

/**
 * Created by yanglikai on 2021/5/28.
 */
public final class EventSender extends AbstractEventNotify {

  private MessageChannel channel;

  private Event event;

  public EventSender() {
  }

  public static EventSender create() {
    return new EventSender();
  }

  /**
   * 事件发送通道
   * <p></p>
   *
   * @param channel 通道
   * @return
   */
  public EventSender channel(MessageChannel channel) {
    this.channel = channel;
    return this;
  }

  /**
   * 事件对象
   * <p></p>
   *
   * @param event 事件对象
   * @return
   */
  public EventSender event(Event event) {
    this.event = event;
    return this;
  }

  /**
   * 事件触发
   * <p></p>
   */
  public void fireEvent() {
    Objects.requireNonNull(this.channel, "channel must be not null");
    Objects.requireNonNull(this.event, "event must be not null");

    super.setOutput(this.channel);
    super.send(this.event);
  }
}
