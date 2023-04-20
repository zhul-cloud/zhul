package com.zhul.cloud.stream.spring.boot;

import org.springframework.cloud.commons.util.SpringFactoryImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created by yanglikai on 2021/1/4.
 */
public class EnableStreamClientImportSelector extends
    SpringFactoryImportSelector<EnableStreamClient> {

  @Override
  public String[] selectImports(AnnotationMetadata metadata) {
    return super.selectImports(metadata);
  }

  @Override
  protected boolean isEnabled() {
    return getEnvironment().getProperty("zhul.cloud.stream.enabled", Boolean.class, Boolean.TRUE);
  }

  @Override
  protected boolean hasDefaultFactory() {
    return true;
  }
}
