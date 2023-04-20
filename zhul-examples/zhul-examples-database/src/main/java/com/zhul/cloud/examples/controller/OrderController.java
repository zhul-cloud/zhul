package com.zhul.cloud.examples.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhul.cloud.common.model.CRUResponse;
import com.zhul.cloud.examples.entity.OrderDO;
import com.zhul.cloud.examples.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yanglikai on 2020/12/9.
 */
@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OrderController {

  @Autowired
  private OrderMapper orderMapper;

  @GetMapping(value = "/orders/{orderCode}")
  public OrderDO getOrder(@PathVariable(value = "orderCode") Integer orderCode) {
    return orderMapper
        .selectOne(new LambdaQueryWrapper<OrderDO>().eq(OrderDO::getOrderCode, orderCode));
  }

  @GetMapping(value = "/orders")
  public Page<OrderDO> getOrders() {
    Page<OrderDO> pages = orderMapper.selectPage(new Page<>(1, 50), new QueryWrapper<>());

    return pages;
  }

  @PostMapping(value = "/orders")
  public CRUResponse createOrder() {

    int fix = 2020121010;

    for (int i = 1; i < 100; i++) {
      OrderDO order = new OrderDO();
      order.setOrderCode(fix + i);
      order.setWarehouseCode("D023");

      orderMapper.insert(order);
    }

    return new CRUResponse(true);
  }

  @PostMapping(value = "/orders/warehouse")
  public CRUResponse createOrderWarehouse() {

    OrderDO order = new OrderDO();
    order.setOrderCode(2021121010);
    order.setWarehouseCode("D021");

    orderMapper.insert(order);

    OrderDO order1 = new OrderDO();
    order1.setOrderCode(2021121010);
    order1.setWarehouseCode("D023");

    orderMapper.insert(order1);

    return new CRUResponse(true);
  }

  @GetMapping(value = "/orders/warehouse/{warehouseCode}")
  public OrderDO getOrderByWarehouse(@PathVariable(value = "warehouseCode") String warehouseCode) {
    return orderMapper
        .selectOne(new LambdaQueryWrapper<OrderDO>().eq(OrderDO::getWarehouseCode, warehouseCode));
  }
}
