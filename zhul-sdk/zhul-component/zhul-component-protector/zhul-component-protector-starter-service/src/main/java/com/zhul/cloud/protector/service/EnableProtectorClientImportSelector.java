package com.zhul.cloud.protector.service;

import org.springframework.cloud.commons.util.SpringFactoryImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created by yanglikai on 2021/1/4.
 */
public class EnableProtectorClientImportSelector extends
    SpringFactoryImportSelector<EnableProtectorClient> {

  @Override
  public String[] selectImports(AnnotationMetadata metadata) {
    return super.selectImports(metadata);
  }

  @Override
  protected boolean isEnabled() {
    return getEnvironment()
        .getProperty("zhul.cloud.protector.enabled", Boolean.class, Boolean.TRUE);
  }

  @Override
  protected boolean hasDefaultFactory() {
    return true;
  }
}
