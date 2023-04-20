package com.zhul.cloud;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.zhul.cloud.feign.OrderCenterFeign;
import com.zhul.cloud.feign.ProductCenterFeign;
import com.zhul.cloud.framework.service.EnableZhulService;
import com.zhul.cloud.sentinel.ZhulRestTemplateBlockHandler;
import com.zhul.cloud.sentinel.ZhulRestTemplateFallbackHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableZhulService
public class ZhulShopApplication {

  public static void main(String[] args) {
    System.setProperty("spring.profiles.active", "shop");

    SpringApplication.run(ZhulShopApplication.class, args);
  }

  @Bean
  @LoadBalanced
  @SentinelRestTemplate(blockHandler = "handleBlock", blockHandlerClass = ZhulRestTemplateBlockHandler.class, fallback = "handleFallback", fallbackClass = ZhulRestTemplateFallbackHandler.class)
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @RestController
  class EchoController {

    @Autowired
    private OrderCenterFeign orderCenterFeign;

    @Autowired
    private ProductCenterFeign productCenterFeign;

    @RequestMapping(value = "/echo", method = RequestMethod.GET)
    public String echo() {

      String order = orderCenterFeign.invoke("zhul-order-center");

      String product = productCenterFeign.invoke("zhul-product-center");

      return order + "|" + product;
    }
  }
}
