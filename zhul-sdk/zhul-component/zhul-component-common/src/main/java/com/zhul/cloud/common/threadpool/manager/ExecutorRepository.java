package com.zhul.cloud.common.threadpool.manager;

import com.zhul.cloud.common.extension.SPI;
import java.util.concurrent.ScheduledExecutorService;

/**
 *
 */
@SPI(value = "default")
public interface ExecutorRepository {

  ScheduledExecutorService getServiceExportExecutor();
}
