package com.zhul.cloud.common.converter;


import org.springframework.beans.BeanUtils;

/**
 * Created by yanglikai on 2019/5/28.
 */
public final class ConverterManager {

  private Converter converter;

  private ConverterManager(Converter converter) {
    this.converter = converter;
  }


  public static ConverterManager builder(Class<? extends Converter> target) {
    Converter converter = BeanUtils.instantiateClass(target);

    return new ConverterManager(converter);
  }

  public <T, R> R convert(T source) {
    return (R) this.converter.convert(source);
  }
}
