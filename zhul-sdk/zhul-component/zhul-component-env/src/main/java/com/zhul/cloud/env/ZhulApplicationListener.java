package com.zhul.cloud.env;

import java.util.LinkedHashMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

/**
 * Created by yanglikai on 2021/1/14.
 */
public class ZhulApplicationListener implements
    ApplicationListener<ApplicationEnvironmentPreparedEvent>,
    Ordered {

  @Override
  public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
    ConfigurableEnvironment environment = event.getEnvironment();

    // 排除自动配置类|消息组件
    if (!environment.getProperty("zhul.cloud.stream.enabled", Boolean.class,
        true)) {

      LinkedHashMap<String, Object> map = new LinkedHashMap<>();
      map.put("spring.autoconfigure.exclude",
          StringUtils.join(AutoConfigurationExcludeManager.excludeToStream(), ","));

      MapPropertySource propertySource = new MapPropertySource("zhulCloudStreamClient", map);
      environment.getPropertySources().addLast(propertySource);
    }

    // 排除自动配置类|缓存组件
    if (!environment.getProperty("zhul.cloud.cache.enabled", Boolean.class,
        true)) {

      LinkedHashMap<String, Object> map = new LinkedHashMap<>();
      map.put("spring.autoconfigure.exclude",
          StringUtils.join(AutoConfigurationExcludeManager.excludeToCache(), ","));

      MapPropertySource propertySource = new MapPropertySource("zhulCloudCacheClient", map);
      environment.getPropertySources().addLast(propertySource);
    }

    // 排除自动配置类|分布式锁组件
    if (!environment.getProperty("zhul.cloud.lock.enabled", Boolean.class,
        true)) {

      LinkedHashMap<String, Object> map = new LinkedHashMap<>();
      map.put("spring.autoconfigure.exclude",
          StringUtils.join(AutoConfigurationExcludeManager.excludeToLock(), ","));

      MapPropertySource propertySource = new MapPropertySource("zhulCloudLockClient", map);
      environment.getPropertySources().addLast(propertySource);
    }

    // 排除自动配置类|分布式事务组件
    if (!environment.getProperty("zhul.cloud.transaction.enabled", Boolean.class,
        true)) {

      LinkedHashMap<String, Object> map = new LinkedHashMap<>();
      map.put("spring.autoconfigure.exclude",
          StringUtils.join(AutoConfigurationExcludeManager.excludeToTransaction(), ","));

      MapPropertySource propertySource = new MapPropertySource("zhulCloudTransactionClient", map);
      environment.getPropertySources().addLast(propertySource);
    }

    // 排除自动配置类|防护组件
    if (!environment.getProperty("zhul.cloud.protector.enabled", Boolean.class,
        true)) {

      LinkedHashMap<String, Object> map = new LinkedHashMap<>();
      map.put("spring.autoconfigure.exclude",
          StringUtils.join(AutoConfigurationExcludeManager.excludeToProtector(), ","));

      MapPropertySource propertySource = new MapPropertySource("zhulCloudProtectorClient", map);
      environment.getPropertySources().addLast(propertySource);
    }

    // 排除自动配置类|数据库组件
    if (!environment.getProperty("zhul.cloud.database.enabled", Boolean.class,
        true)) {

      LinkedHashMap<String, Object> map = new LinkedHashMap<>();
      map.put("spring.autoconfigure.exclude",
          StringUtils.join(AutoConfigurationExcludeManager.excludeToDatabase(), ","));

      MapPropertySource propertySource = new MapPropertySource("zhulCloudDatabaseClient", map);
      environment.getPropertySources().addLast(propertySource);
    }

    // 排除自动配置类|配置中心组件
    if (!environment.getProperty("zhul.cloud.config.enabled", Boolean.class,
        true)) {

      LinkedHashMap<String, Object> map = new LinkedHashMap<>();
      map.put("spring.autoconfigure.exclude",
          StringUtils.join(AutoConfigurationExcludeManager.excludeToConfig(), ","));
      map.put("spring.cloud.nacos.config.enabled", false);

      MapPropertySource propertySource = new MapPropertySource("zhulCloudConfigClient", map);
      environment.getPropertySources().addLast(propertySource);
    }

    // 排除自动配置类|注册发现组件
    if (!environment.getProperty("zhul.cloud.discovery.enabled", Boolean.class,
        true)) {

      LinkedHashMap<String, Object> map = new LinkedHashMap<>();
      map.put("spring.autoconfigure.exclude",
          StringUtils.join(AutoConfigurationExcludeManager.excludeToDiscovery(), ","));
      map.put("spring.cloud.config.discovery.enabled", false);

      MapPropertySource propertySource = new MapPropertySource("zhulCloudDiscoveryClient", map);
      environment.getPropertySources().addLast(propertySource);
    }

    // 排除自动配置类|Nepxion组件
    if (!environment.getProperty("zhul.cloud.nepxion.enabled", Boolean.class,
        true)) {

      System.setProperty("nepxion.banner.shown", "false");

      LinkedHashMap<String, Object> map = new LinkedHashMap<>();
      map.put("spring.autoconfigure.exclude",
          StringUtils.join(AutoConfigurationExcludeManager.excludeToNepxion(), ","));

      MapPropertySource propertySource = new MapPropertySource("zhulCloudNepxionClient", map);
      environment.getPropertySources().addLast(propertySource);
    }
  }

  @Override
  public int getOrder() {
    return 100;
  }
}
