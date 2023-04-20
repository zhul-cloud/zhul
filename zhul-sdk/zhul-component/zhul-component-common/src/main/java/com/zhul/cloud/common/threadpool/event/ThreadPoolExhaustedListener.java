package com.zhul.cloud.common.threadpool.event;

public interface ThreadPoolExhaustedListener {

  void onEvent(ThreadPoolExhaustedEvent event);
}
