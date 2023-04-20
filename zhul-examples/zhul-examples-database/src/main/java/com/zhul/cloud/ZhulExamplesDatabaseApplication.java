package com.zhul.cloud;

import com.zhul.cloud.framework.service.EnableZhulService;
import org.springframework.boot.SpringApplication;

@EnableZhulService
public class ZhulExamplesDatabaseApplication {

  public static void main(String[] args) {
    SpringApplication.run(ZhulExamplesDatabaseApplication.class, args);
  }
}
