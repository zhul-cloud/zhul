package com.zhul.cloud.lock.spring.boot;

import org.springframework.cloud.commons.util.SpringFactoryImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created by yanglikai on 2021/1/4.
 */
public class EnableLockClientImportSelector extends
    SpringFactoryImportSelector<EnableLockClient> {

  @Override
  public String[] selectImports(AnnotationMetadata metadata) {
    return super.selectImports(metadata);
  }

  @Override
  protected boolean isEnabled() {
    return getEnvironment().getProperty("zhul.cloud.lock.enabled", Boolean.class, Boolean.TRUE);
  }

  @Override
  protected boolean hasDefaultFactory() {
    return true;
  }
}
