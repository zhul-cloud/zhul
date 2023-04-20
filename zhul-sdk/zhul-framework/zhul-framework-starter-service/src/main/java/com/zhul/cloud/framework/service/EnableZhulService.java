package com.zhul.cloud.framework.service;

import com.zhul.cloud.alarm.spring.boot.EnableAlarmClient;
import com.zhul.cloud.cache.spring.boot.EnableCacheClient;
import com.zhul.cloud.core.config.EnableGlobalExceptionHandler;
import com.zhul.cloud.core.config.EnableGlobalResponseBodyHandler;
import com.zhul.cloud.core.config.EnableKnife4jClient;
import com.zhul.cloud.database.spring.boot.EnableDatabaseClient;
import com.zhul.cloud.lock.spring.boot.EnableLockClient;
import com.zhul.cloud.protector.service.EnableProtectorClient;
import com.zhul.cloud.stream.spring.boot.EnableStreamClient;
import com.zhul.cloud.transaction.spring.boot.EnableTransactionClient;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Created by yanglikai on 2020/12/30.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.wanquan", "com.wq"})
@EnableDiscoveryClient
@EnableCacheClient
@EnableLockClient
@EnableStreamClient
@EnableTransactionClient
@EnableProtectorClient
@EnableDatabaseClient
@EnableGlobalExceptionHandler
@EnableGlobalResponseBodyHandler
@EnableKnife4jClient
@EnableAsync(proxyTargetClass = true)
@EnableAlarmClient
@Import(FeignClientConfig.class)
public @interface EnableZhulService {

}
