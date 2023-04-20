package com.zhul.cloud.controller;

import com.zhul.cloud.common.api.LockProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yanglikai on 2020/12/29.
 */
@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LockController {

  @Autowired
  private LockProvider lockProvider;


  @PostMapping(value = "/lock/")
  public void lock() {
    lockProvider.lock("lisi");
  }

  @PostMapping(value = "/try/lock")
  public boolean tryLock() {
    return lockProvider.tryLock("zhangsan");
  }
}
