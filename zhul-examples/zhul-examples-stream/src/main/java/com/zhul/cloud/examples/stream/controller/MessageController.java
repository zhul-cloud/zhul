package com.zhul.cloud.examples.stream.controller;

import com.zhul.cloud.common.model.CRUResponse;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yanglikai on 2021/1/4.
 */
@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MessageController {

  @Autowired(required = false)
  private MessageChannel output;

  @PostMapping(value = "/messages")
  public CRUResponse push() {
    Order order = new Order();
    order.setOrderCode("PO10000001");
    order.setUserId(1000000001L);

    String msg = "hello rocket mq!";

    Map<String, Object> headers = new HashMap<>();
    headers.put(MessageConst.PROPERTY_TAGS, "ORDER_PAY_NOTIFY");
    headers.put(MessageConst.PROPERTY_KEYS, order.getOrderCode());

    Message<Order> message = MessageBuilder.createMessage(order, new MessageHeaders(headers));

//    Message message = MessageBuilder.withPayload(order).build();
    output.send(message);

    return new CRUResponse();
  }

  @Data
  public static class Order {
    private String orderCode;

    private Long userId;
  }
}
