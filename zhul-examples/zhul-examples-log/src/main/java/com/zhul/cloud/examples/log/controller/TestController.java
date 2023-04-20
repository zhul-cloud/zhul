package com.zhul.cloud.examples.log.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yanglikai on 2021/1/8.
 */
@Slf4j
@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TestController {

  @GetMapping(value = "/logs/info")
  public void info() {
    log.info("订单创建成功");
  }

  @GetMapping(value = "/logs/error")
  public void error() {
    log.error("订单状态更新失败");
  }

  @GetMapping(value = "/logs/warn")
  public void warn() {
    log.warn("订单推送数据不一致");
  }
}
