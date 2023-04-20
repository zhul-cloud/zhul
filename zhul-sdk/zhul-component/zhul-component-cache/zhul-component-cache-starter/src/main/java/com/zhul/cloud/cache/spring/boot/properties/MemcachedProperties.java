package com.zhul.cloud.cache.spring.boot.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by yanglikai on 2021/1/4.
 */
@Data
@ConfigurationProperties(value = "zhul.cloud.cache.memcached")
public class MemcachedProperties {

  private boolean enabled = false;
}
