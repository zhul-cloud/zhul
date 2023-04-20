package com.zhul.cloud.database.sharding;

import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yanglikai on 2022/11/30.
 */
@Configuration
@ConditionalOnProperty(value = "zhul.cloud.database.enabled", matchIfMissing = true)
public class ShardingAutoConfiguration {

  @Bean
  public ShardingDatabaseIdProvider databaseIdProvider() {
    return new ShardingDatabaseIdProvider(new VendorDatabaseIdProvider());
  }
}
