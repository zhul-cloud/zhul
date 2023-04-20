package com.zhul.cloud.examples.controller;

import com.zhul.cloud.examples.entity.OrderDO;
import com.zhul.cloud.examples.entity.UserDO;
import com.zhul.cloud.examples.mapper.OrderMapper;
import com.zhul.cloud.examples.mapper.UserMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yanglikai on 2020/12/9.
 */
@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TransactionController {

  @Autowired
  private OrderMapper orderMapper;

  @Autowired
  private UserMapper userMapper;

  @SneakyThrows(Exception.class)
  @Transactional(rollbackFor = Exception.class)
  @PostMapping(value = "/transactions")
  public boolean transaction() {
    OrderDO order = new OrderDO();
//    order.setOrderCode("OM20201210100000");
    orderMapper.insert(order);

    UserDO user = new UserDO();
    user.setName("yanglikai");
    user.setMobile("13577895656");
    userMapper.insert(user);

    throw new Exception();
  }
}
