package com.zhul.cloud.security.spring.boot.sign;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by yanglikai on 2021/9/1.
 */
@Configuration
@ConditionalOnProperty(value = "zhul.cloud.security.enabled")
public class SignatureAuthorizationAutoConfiguration implements WebMvcConfigurer {

  @Value("${zhul.cloud.security.sign.filterPath}")
  private String[] filterPath;

  @Value("${zhul.cloud.security.sign.privateKey}")
  private String privateKey;

  @Value("${zhul.cloud.security.sign.interceptPath:}")
  private String[] interceptPath;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(signatureAuthorizationInterceptor());
  }

  @Bean
  public FilterRegistrationBean registerFilter() {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(httpServletFilter());
    registration.addUrlPatterns(this.filterPath);
    registration.setOrder(1);
    return registration;
  }

  @Bean
  public SignatureAuthorizationInterceptor signatureAuthorizationInterceptor() {
    return new SignatureAuthorizationInterceptor(this.privateKey, this.interceptPath);
  }

  @Bean
  public HttpServletFilter httpServletFilter() {
    return new HttpServletFilter();
  }
}
