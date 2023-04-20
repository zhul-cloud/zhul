package com.zhul.cloud.common.threadpool.manager;

import cn.hutool.core.thread.NamedThreadFactory;
import com.zhul.cloud.common.extension.Join;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 *
 */
@Join
public class DefaultExecutorRepository implements ExecutorRepository {

  private volatile ScheduledExecutorService serviceExportExecutor;

  private final Object LOCK = new Object();

  @Override
  public ScheduledExecutorService getServiceExportExecutor() {
    synchronized (LOCK) {
      if (serviceExportExecutor == null) {
        int coreSize = getExportThreadNum();
        serviceExportExecutor = new ScheduledThreadPoolExecutor(coreSize,
            new NamedThreadFactory("Zhul" + "-service-export", true));
      }
    }
    return serviceExportExecutor;
  }

  private Integer getExportThreadNum() {
    return Runtime.getRuntime().availableProcessors();
  }
}
