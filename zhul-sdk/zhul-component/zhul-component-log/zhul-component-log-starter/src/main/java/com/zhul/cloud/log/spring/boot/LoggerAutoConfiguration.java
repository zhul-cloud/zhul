package com.zhul.cloud.log.spring.boot;

import com.zhul.cloud.log.http.HttpLoggerAutoConfiguration;
import java.util.concurrent.Executor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 日志模块自动配置
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/9/30
 */
@Configuration
@ConditionalOnProperty(value = "zhul.cloud.log.enabled")
@Import(HttpLoggerAutoConfiguration.class)
public class LoggerAutoConfiguration {

  @Bean
  public Executor zhulCloudLogAsyncExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    //核心线程数
    executor.setCorePoolSize(5);
    //线程池维护线程的最大数量,只有在缓冲队列满了之后才会申请超过核心线程数的线程
    executor.setMaxPoolSize(10);
    //缓存队列
    executor.setQueueCapacity(5);
    //允许的空闲时间,当超过了核心线程数之外的线程在空闲时间到达之后会被销毁
    executor.setKeepAliveSeconds(30);
    executor.setThreadNamePrefix("zhul-log-async-");
    executor.initialize();

    return executor;
  }

}
