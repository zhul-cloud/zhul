package com.zhul.cloud.feign;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.nepxion.discovery.common.constant.DiscoveryConstant;
import com.zhul.cloud.sentinel.ZhulRestTemplateBlockHandler;
import com.zhul.cloud.sentinel.ZhulRestTemplateFallbackHandler;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yanglikai on 2020/12/11.
 */
@RestController
@ConditionalOnProperty(name = DiscoveryConstant.SPRING_APPLICATION_NAME, havingValue = "zhul-order-center")
public class OrderCenterFeignImpl extends AbstractFeignImpl implements OrderCenterFeign {
  private static final Logger LOG = LoggerFactory.getLogger(OrderCenterFeignImpl.class);

  private volatile int count = 0;

  @SentinelResource(value = "invoke",
      blockHandler = "handleBlock",
      fallback = "handleFallback")
  @Override
  @SneakyThrows(Exception.class)
  public String invoke(String value) {
    value = doInvoke(value);

    LOG.info("调用路径：{}", value);

    //if (count > 10) {
    //  throw new Exception("");
    //}

    count++;
    return value;
  }

  @Override
  public String getOrder(Integer id) {
    return String.valueOf(id);
  }

  public String handleBlock(String value, BlockException e) {
    e.printStackTrace();

    return "handleBlock, error occurred at " + value;
  }

  public String handleFallback(String value, Throwable e) {
    e.printStackTrace();

    return "handleFallback, error occurred at " + value;
  }
}
