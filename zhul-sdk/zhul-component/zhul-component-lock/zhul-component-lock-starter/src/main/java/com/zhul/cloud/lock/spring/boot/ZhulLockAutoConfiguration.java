package com.zhul.cloud.lock.spring.boot;

import com.zhul.cloud.common.api.LockProvider;
import com.zhul.cloud.lock.redis.RedissonLockClient;
import com.zhul.cloud.lock.zookeeper.ZookeeperLockClient;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by yanglikai on 2021/1/4.
 */
@Configuration(proxyBeanMethods = false)
@Import(ZhulLockConfiguration.class)
@ConditionalOnProperty(value = "zhul.cloud.lock.enabled", matchIfMissing = true)
public class ZhulLockAutoConfiguration {

  @Autowired(required = false)
  private RedissonClient redissonClient;

  @Bean(value = "redisLockProvider")
  @ConditionalOnProperty(value = "zhul.cloud.lock.redis.enabled", matchIfMissing = true)
  public LockProvider redissonLockProvider() {
    return new RedissonLockClient(redissonClient);
  }

  @Bean(value = "zookeeperLockProvider")
  @ConditionalOnProperty(value = "zhul.cloud.lock.zookeeper.enabled")
  public LockProvider zookeeperLockProvider() {
    return new ZookeeperLockClient();
  }
}
