package com.zhul.cloud.common.api;

import java.util.concurrent.TimeUnit;

/**
 * Created by yanglikai on 2020/12/31.
 */
public interface LockProvider {

  void lock(String name);

  void lockInterruptibly(String name) throws InterruptedException;

  boolean tryLock(String name);

  boolean tryLock(String name, long time, TimeUnit unit) throws InterruptedException;

  void unlock(String name);
}
