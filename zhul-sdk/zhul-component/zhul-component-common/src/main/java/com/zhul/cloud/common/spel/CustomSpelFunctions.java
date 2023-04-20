package com.zhul.cloud.common.spel;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

/**
 * 自定义el表达式方法
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/6/22
 */
public class CustomSpelFunctions {

  public static Object joinToString(List<Object> list, String delimiter) {
    return list.stream().map(Object::toString).collect(Collectors.joining(delimiter));
  }

  public static boolean isNotBlank(String param) {
    return StringUtils.isNotBlank(param);
  }
}
