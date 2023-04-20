package com.zhul.cloud.log.http;

import com.zhul.cloud.common.logger.BizLoggerHandler;
import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/9/30
 */
@Configuration
@ConditionalOnProperty(value = "zhul.cloud.log.enabled")
public class HttpLoggerAutoConfiguration {

  @Bean
  public BizLoggerHandler bizLoggerHandler() {
    return new HttpBizLoggerHandler();
  }

}
