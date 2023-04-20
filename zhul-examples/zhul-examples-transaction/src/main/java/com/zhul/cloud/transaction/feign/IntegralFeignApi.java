package com.zhul.cloud.transaction.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by yanglikai on 2021/1/13.
 */
@FeignClient(value = "seata-integral-center")
public interface IntegralFeignApi {

  @PostMapping(value = "/v1/integral/debit")
  void debit(@RequestParam(value = "userId") String userId,
      @RequestParam(value = "money") int money);

  @PostMapping(value = "/v1/integral/debit/tcc")
  void tccDebit(@RequestParam(value = "userId") String userId,
      @RequestParam(value = "money") int money);
}
