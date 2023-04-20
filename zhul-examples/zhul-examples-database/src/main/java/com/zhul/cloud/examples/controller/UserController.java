package com.zhul.cloud.examples.controller;

import com.zhul.cloud.examples.entity.UserDO;
import com.zhul.cloud.examples.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yanglikai on 2020/12/9.
 */
@RestController
@RequestMapping(value="/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

  @Autowired
  private UserMapper userMapper;

  @GetMapping(value = "/users/{id}")
  public String getUser(@PathVariable(value = "id") Integer id) {
    UserDO user = userMapper.selectById(id);

    return user.toString();
  }

  @PostMapping(value = "/users")
  public boolean createUser(UserDO user) {
    return userMapper.insert(user) > 0 ? true : false;
  }
}
