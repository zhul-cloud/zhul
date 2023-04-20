package com.zhul.cloud.controller;

import com.zhul.cloud.common.api.CacheProvider;
import com.zhul.cloud.enums.RedisConstant;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yanglikai on 2020/12/29.
 */
@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CacheController {

  @Autowired
  private CacheProvider cacheProvider;


  @PostMapping(value = "/caches/{name}")
  public String setCache(@PathVariable(value = "name") String name) {
    String key = String.format(RedisConstant.USER_NAME.key, name);

    cacheProvider.set(key, name, RedisConstant.USER_NAME.expired);

    return name;
  }

  @GetMapping(value = "/caches/{name}")
  public String getCache(@PathVariable(value = "name") String name) {
    String key = String.format(RedisConstant.USER_NAME.key, name);

    String value = cacheProvider.get(key);

    return value;
  }

  @PostMapping(value = "/caches/object")
  public String setObjectCache(@RequestBody User user) {
    String key = String.format(RedisConstant.BY_USER.key, user.getUserId());

    cacheProvider.set(key, user, RedisConstant.USER_NAME.expired);

    return user.toString();
  }

  @GetMapping(value = "/caches/object/{userId}")
  public User setObjectCache(@PathVariable(value = "userId") Integer userId) {
    String key = String.format(RedisConstant.BY_USER.key, userId);

    User user = cacheProvider.get(key, User.class);

    return user;
  }

  @Data
  public static class User {

    private int userId;

    private String userName;

    private String mobile;
  }
}
