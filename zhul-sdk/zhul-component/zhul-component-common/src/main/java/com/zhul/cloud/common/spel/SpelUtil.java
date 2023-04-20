package com.zhul.cloud.common.spel;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * el表达式处理
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/12/6
 */
@Slf4j
public class SpelUtil {

  public static String parse(String content, Map<String, Object> params) {
    if (StringUtils.isBlank(content)) {
      return null;
    }
    SpelExpressionParser parser = new SpelExpressionParser(new SpelParserConfiguration(true, true));
    StandardEvaluationContext context = new StandardEvaluationContext(params);
    context.setPropertyAccessors(Arrays.asList(MapSpelAccessor.getInstance()));
    try {
      context.registerFunction("joinToString",
          CustomSpelFunctions.class.getDeclaredMethod("joinToString", List.class, String.class));
    } catch (NoSuchMethodException e) {
      log.error(e.toString(), e);
    }
    try {
      context.registerFunction("isNotBlank",
          CustomSpelFunctions.class.getDeclaredMethod("isNotBlank", String.class));
    } catch (NoSuchMethodException e) {
      log.error(e.toString(), e);
    }
    return parser.parseExpression(content,
        new TemplateParserContext()).getValue(context, String.class);
  }

}
