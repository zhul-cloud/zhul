package com.zhul.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yanglikai on 2020/12/10.
 */
@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RefreshScope
public class UserController {

  @Value(value = "${user.name}")
  private String name;

  @GetMapping(value = "/users/{id}")
  public String getUser(@PathVariable(value = "id") Integer id) {
    return name;
  }
}
