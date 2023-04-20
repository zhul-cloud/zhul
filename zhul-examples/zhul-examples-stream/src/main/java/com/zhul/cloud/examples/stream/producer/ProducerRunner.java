package com.zhul.cloud.examples.stream.producer;

import java.util.HashMap;
import java.util.Map;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

/**
 * Created by yanglikai on 2021/1/4.
 */
public class ProducerRunner implements CommandLineRunner {

  @Autowired
  private MessageChannel output;


  @Override
  public void run(String... args) throws Exception {
    String msg = "hello rocket mq!";

    Map<String, Object> headers = new HashMap<>();
    headers.put(MessageConst.PROPERTY_TAGS, "tagStr");
    Message message = MessageBuilder.createMessage(msg, new MessageHeaders(headers));
    output.send(message);
  }
}
