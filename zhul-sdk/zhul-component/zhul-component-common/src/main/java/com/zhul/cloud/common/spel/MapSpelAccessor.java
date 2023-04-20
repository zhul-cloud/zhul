package com.zhul.cloud.common.spel;

import java.util.Map;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.AccessException;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.TypedValue;

/**
 * 自定义el表达式处理
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/6/22
 */
public class MapSpelAccessor extends MapAccessor {

  private static final MapAccessor INSTANCE = new MapSpelAccessor();

  private MapSpelAccessor() {
  }

  public static final MapAccessor getInstance() {
    return INSTANCE;
  }

  @Override
  public boolean canRead(EvaluationContext context, Object target, String name) {
    return true;
  }

  @Override
  public TypedValue read(EvaluationContext context, Object target, String name)
      throws AccessException {
    Map<?, ?> map = (Map<?, ?>) target;
    Object value = map.get(name);
    return value == null ? TypedValue.NULL : new TypedValue(value);
  }
}