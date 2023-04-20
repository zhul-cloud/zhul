package com.zhul.cloud;

import com.zhul.cloud.framework.service.EnableZhulService;
import org.springframework.boot.SpringApplication;

@EnableZhulService
public class SeataOrderServiceApplication {

  public static void main(String[] args) {
    System.setProperty("spring.profiles.active", "order-center");

    SpringApplication.run(SeataOrderServiceApplication.class, args);
  }

}
