package com.zhul.cloud.common.converter;

/**
 * Created by yanglikai on 2019/5/28.
 */
@FunctionalInterface
public interface Converter<T, R> {

  R convert(T source);
}
