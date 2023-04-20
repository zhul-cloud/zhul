package com.zhul.cloud.cache.spring.boot.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by yanglikai on 2021/1/4.
 */
@Data
@ConfigurationProperties(value = "zhul.cloud.cache.j2cache")
public class J2CacheProperties {

  private boolean enabled = true;
}
