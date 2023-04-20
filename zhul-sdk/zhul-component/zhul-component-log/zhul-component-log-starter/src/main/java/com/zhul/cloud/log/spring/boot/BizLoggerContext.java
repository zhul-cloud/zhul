package com.zhul.cloud.log.spring.boot;

import com.google.common.collect.Maps;
import java.util.Map;

/**
 * 日志上下文
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/10/9
 */
public class BizLoggerContext {

  private static ThreadLocal<Map<String, Object>> context = new ThreadLocal<>();

  public static void setContext(String key, Object value) {
    Map<String, Object> map = context.get();
    if (map == null) {
      map = Maps.newHashMap();
    }
    map.put(key, value);
    context.set(map);
  }

  public static Object getContext(String key) {
    Map<String, Object> map = context.get();
    if (map != null) {
      return map.get(key);
    } else {
      return null;
    }
  }

  public static void setContexts(Map<String, Object> map) {
    context.set(map);
  }

  public static Map<String, Object> getContexts() {
    return context.get();
  }

  public static void removeContexts() {
    context.remove();
  }
}
