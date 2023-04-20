package com.zhul.cloud.common.validation.mobile;

import cn.hutool.core.lang.Validator;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;
import org.springframework.util.StringUtils;

/**
 * 手机号码验证方法
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/1/7
 */
public class MobileValidator implements ConstraintValidator<Mobile, String> {

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    if (!StringUtils.isEmpty(s)) {
      return Validator.isMobile(s);
    }
    return true;
  }
}  