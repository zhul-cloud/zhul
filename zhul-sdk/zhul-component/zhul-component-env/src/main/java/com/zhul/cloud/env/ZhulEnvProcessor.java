package com.zhul.cloud.env;

import com.zhul.cloud.env.constant.ZhulEnvConstant;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * Created by yanglikai on 2020/12/18.
 */
@Slf4j
public abstract class ZhulEnvProcessor implements EnvironmentPostProcessor, Ordered {

  private static final String OPERATING_SYSTEM_NAME = System.getProperty("os.name")
      .toLowerCase(Locale.ENGLISH);

  @Override
  public void postProcessEnvironment(ConfigurableEnvironment environment,
      SpringApplication application) {
    if (StringUtils.equals(environment.getClass().getName(), StandardEnvironment.class.getName())) {
      Properties serverProperties = loadServerProperties();
      if (serverProperties == null) {
        throw new IllegalArgumentException("server.properties must not be null");
      }

      setup4CommonProperties();

      setup4EnvProperties(serverProperties);
    }
  }

  @Override
  public int getOrder() {
    return Ordered.HIGHEST_PRECEDENCE;
  }

  public abstract String getPrefixName();

  protected Properties loadServerProperties() {
    try {
      if (OPERATING_SYSTEM_NAME.contains("win")) {
        return PropertiesLoaderUtils.loadProperties(
            new InputStreamResource(
                new FileInputStream(ZhulEnvConstant.SERVER_PROPERTIES_PATH_WINDOWS)));
      } else {
        return PropertiesLoaderUtils.loadProperties(
            new InputStreamResource(
                new FileInputStream(ZhulEnvConstant.SERVER_PROPERTIES_PATH_LINUX)));
      }
    } catch (IOException e) {
    }

    return loadServerPropertiesByLocal();
  }

  private Properties loadServerPropertiesByLocal() {
    try {
      return PropertiesLoaderUtils.loadAllProperties(ZhulEnvConstant.SERVER_PROPERTIES_PATH_LOCAL);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  private void setup4CommonProperties() {
    try {
      String config = getPrefixName() + ZhulEnvConstant.REGION_SEPARATE + "common" + ".properties";

      Properties properties = PropertiesLoaderUtils.loadAllProperties(config);
      properties.stringPropertyNames().forEach(el -> {
        System.setProperty(el, properties.getProperty(el));
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void setup4EnvProperties(Properties serverProperties) {
    String region = serverProperties.getProperty(ZhulEnvConstant.REGION_NAME,
        ZhulEnvConstant.REGION_SEPARATE + ZhulEnvConstant.REGION_VALUE);
    System.setProperty(ZhulEnvConstant.REGION_NAME, ZhulEnvConstant.REGION_SEPARATE + region);

    String domain = serverProperties
        .getProperty(ZhulEnvConstant.DOMAIN_NAME, ZhulEnvConstant.DOMAIN_VALUE);
    System.setProperty(ZhulEnvConstant.DOMAIN_NAME, domain);

    String env = serverProperties.getProperty(ZhulEnvConstant.ENV_NAME, ZhulEnvConstant.ENV_VALUE)
        .toLowerCase();
    System.setProperty(ZhulEnvConstant.ENV_NAME, env);

    try {
      String config = getPrefixName() + ZhulEnvConstant.REGION_SEPARATE + env + ".properties";

      Properties properties = PropertiesLoaderUtils.loadAllProperties(config);
      properties.stringPropertyNames().forEach(el -> {
        System.setProperty(el, properties.getProperty(el));
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
