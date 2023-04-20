package com.zhul.cloud.lock.spring.boot;

import com.zhul.cloud.lock.spring.boot.properties.RedissonLockProperties;
import com.zhul.cloud.lock.spring.boot.properties.ZookeeperLockProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by yanglikai on 2021/1/4.
 */
@Data
@ConfigurationProperties(value = "zhul.cloud.lock")
public class ZhulLockProperties {

  private boolean enabled = true;

  private RedissonLockProperties redis;

  private ZookeeperLockProperties zookeeper;
}
