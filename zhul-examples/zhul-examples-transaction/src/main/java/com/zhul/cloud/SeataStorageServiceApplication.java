package com.zhul.cloud;

import com.zhul.cloud.framework.service.EnableZhulService;
import org.springframework.boot.SpringApplication;

@EnableZhulService
public class SeataStorageServiceApplication {

  public static void main(String[] args) {
    System.setProperty("spring.profiles.active", "storage-center");

    SpringApplication.run(SeataStorageServiceApplication.class, args);
  }

}
