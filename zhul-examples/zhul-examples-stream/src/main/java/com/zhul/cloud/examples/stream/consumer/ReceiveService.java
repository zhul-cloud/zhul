package com.zhul.cloud.examples.stream.consumer;

import com.zhul.cloud.examples.stream.controller.MessageController;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

/**
 * Created by yanglikai on 2021/1/4.
 */
@Service
public class ReceiveService {

  @StreamListener(value = Sink.INPUT)
  public void receiveInput(MessageController.Order receiveMsg) {
    System.out.println("input receive: " + receiveMsg);
  }
}
