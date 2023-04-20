package com.zhul.cloud.feign;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.nepxion.discovery.common.constant.DiscoveryConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yanglikai on 2020/12/11.
 */
@RestController
@ConditionalOnProperty(name = DiscoveryConstant.SPRING_APPLICATION_NAME, havingValue = "zhul-product-center")
public class ProductCenterFeignImpl extends AbstractFeignImpl implements ProductCenterFeign {
  private static final Logger LOG = LoggerFactory.getLogger(ProductCenterFeignImpl.class);

  @SentinelResource(value = "invoke")
  @Override
  public String invoke(String value) {
    value = doInvoke(value);

    LOG.info("调用路径：{}", value);

    return value;
  }
}
