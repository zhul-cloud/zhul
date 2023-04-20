package com.zhul.cloud.common.stream.evnet;

/**
 * 事件处理器
 * <p></p>
 * Created by yanglikai on 2021/7/22.
 */
public interface EventProcessor<T, R> {

  R handle(T target);
}
