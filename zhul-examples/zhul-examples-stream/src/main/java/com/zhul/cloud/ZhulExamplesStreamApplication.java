package com.zhul.cloud;

import com.zhul.cloud.framework.service.EnableZhulService;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;

@EnableZhulService
@EnableBinding({Source.class, Sink.class})
public class ZhulExamplesStreamApplication {

  public static void main(String[] args) {
    SpringApplication.run(ZhulExamplesStreamApplication.class, args);
  }

}
