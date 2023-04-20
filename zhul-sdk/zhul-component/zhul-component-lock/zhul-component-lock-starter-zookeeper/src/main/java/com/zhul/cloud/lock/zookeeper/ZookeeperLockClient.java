package com.zhul.cloud.lock.zookeeper;

import com.zhul.cloud.common.api.LockProvider;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanglikai on 2021/1/4.
 */
public class ZookeeperLockClient implements LockProvider {

  @Override
  public void lock(String name) {

  }

  @Override
  public void lockInterruptibly(String name) throws InterruptedException {

  }

  @Override
  public boolean tryLock(String name) {
    return false;
  }

  @Override
  public boolean tryLock(String name, long time, TimeUnit unit) throws InterruptedException {
    return false;
  }

  @Override
  public void unlock(String name) {

  }
}
