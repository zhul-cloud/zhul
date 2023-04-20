package com.zhul.cloud;

import com.zhul.cloud.framework.service.EnableZhulService;
import org.springframework.boot.SpringApplication;

@EnableZhulService
public class SeataPlaceOrderServiceApplication {

  public static void main(String[] args) {
    System.setProperty("spring.profiles.active", "biz-center");

    SpringApplication.run(SeataPlaceOrderServiceApplication.class, args);
  }

}
