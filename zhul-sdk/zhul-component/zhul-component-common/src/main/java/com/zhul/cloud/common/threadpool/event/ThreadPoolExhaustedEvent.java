package com.zhul.cloud.common.threadpool.event;

/**
 * An Event when the Zhul thread pool is exhausted.
 */
public class ThreadPoolExhaustedEvent {

  final String msg;

  public ThreadPoolExhaustedEvent(String msg) {
    this.msg = msg;
  }

  public String getMsg() {
    return msg;
  }
}
