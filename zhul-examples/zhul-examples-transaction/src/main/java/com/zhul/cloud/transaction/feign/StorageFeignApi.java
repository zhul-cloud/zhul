package com.zhul.cloud.transaction.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by yanglikai on 2021/1/13.
 */
@FeignClient(value = "seata-storage-center")
public interface StorageFeignApi {

  @PostMapping(value = "/v1/storage/deduct")
  void deduct(@RequestParam(value = "productCode") String productCode,
      @RequestParam(value = "count") int count);

  @PostMapping(value = "/v1/storage/deduct/tcc")
  void tccDeduct(@RequestParam(value = "productCode") String productCode,
      @RequestParam(value = "count") int count);
}
