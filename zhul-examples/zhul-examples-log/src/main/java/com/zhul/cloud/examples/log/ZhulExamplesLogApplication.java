package com.zhul.cloud.examples.log;

import com.zhul.cloud.framework.service.EnableZhulService;
import org.springframework.boot.SpringApplication;

@EnableZhulService
public class ZhulExamplesLogApplication {

  public static void main(String[] args) {
    System.setProperty("nacos.logging.default.config.enabled", "false");


    SpringApplication.run(ZhulExamplesLogApplication.class, args);
  }

}
