package com.zhul.cloud.lock.spring.boot.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by yanglikai on 2021/1/4.
 */
@Data
@ConfigurationProperties(value = "zhul.cloud.lock.redis")
public class RedissonLockProperties {

  private boolean enabled = true;
}
