package com.zhul.cloud.database.spring.boot;

import org.springframework.cloud.commons.util.SpringFactoryImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created by yanglikai on 2021/1/4.
 */
public class EnableDatabaseClientImportSelector extends
    SpringFactoryImportSelector<EnableDatabaseClient> {

  @Override
  public String[] selectImports(AnnotationMetadata metadata) {
    return super.selectImports(metadata);
  }

  @Override
  protected boolean isEnabled() {
    return getEnvironment()
        .getProperty("zhul.cloud.database.enabled", Boolean.class, Boolean.TRUE);
  }

  @Override
  protected boolean hasDefaultFactory() {
    return true;
  }
}
