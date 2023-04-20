package com.zhul.cloud;

import com.zhul.cloud.framework.service.EnableZhulService;
import org.springframework.boot.SpringApplication;

@EnableZhulService
public class SeataIntegralServiceApplication {

  public static void main(String[] args) {
    System.setProperty("spring.profiles.active", "integral-center");

    SpringApplication.run(SeataIntegralServiceApplication.class, args);
  }

}
