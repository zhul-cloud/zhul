package com.zhul.cloud.framework.api;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/1/15
 */
@Configuration
@ConditionalOnProperty({"feign.okhttp.enabled"})
public class FeignClientConfig {
    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient().newBuilder().connectTimeout(20000, TimeUnit.MILLISECONDS)
                .readTimeout(20000, TimeUnit.MILLISECONDS)
                .writeTimeout(20000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .connectionPool(new ConnectionPool()).build();
    }

}
