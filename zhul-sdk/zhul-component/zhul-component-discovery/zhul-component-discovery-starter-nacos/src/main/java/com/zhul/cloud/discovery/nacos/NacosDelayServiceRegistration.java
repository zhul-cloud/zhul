package com.zhul.cloud.discovery.nacos;


import com.alibaba.cloud.nacos.registry.NacosAutoServiceRegistration;
import com.alibaba.cloud.nacos.registry.NacosRegistration;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationProperties;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.client.serviceregistry.ServiceRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author zhangzheyuan
 */
@Slf4j
@Primary
@Configuration
@Endpoint(id = "register-status")
@ConditionalOnProperty(value = "spring.cloud.nacos.discovery.delay.enabled", havingValue = "true")
public class NacosDelayServiceRegistration extends NacosAutoServiceRegistration {

  @Value("${spring.cloud.nacos.discovery.delay.time:60}")
  private Integer delaySeconds;
  private static boolean registered = false;

  public NacosDelayServiceRegistration(
      ServiceRegistry<Registration> serviceRegistry,
      AutoServiceRegistrationProperties autoServiceRegistrationProperties,
      NacosRegistration registration) {
    super(serviceRegistry, autoServiceRegistrationProperties, registration);
  }

  @ReadOperation
  public ResponseEntity isRegistered() throws Exception {
    if (registered) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }
  }

  @SneakyThrows
  @Override
  protected void register() {
    Thread.sleep(delaySeconds * 1000);
    super.register();
    registered = true;
    log.info("注册=======================================完成");
  }
}
