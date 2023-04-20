package com.zhul.cloud.common.logger;

/**
 * 操作用户处理器
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/9/30
 */
public interface OptUserHandler {

  /**
   * 生成OptUser对象
   *
   * @param args 方法参数
   * @return
   */
  OptUser generate(Object... args);

}
