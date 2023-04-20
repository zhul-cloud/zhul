package com.zhul.cloud.core.config;

import com.zhul.cloud.core.config.doc.Knife4jClientConfig;
import com.zhul.cloud.core.config.web.GlobalExceptionHandler;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * TODO
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/1/6
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(Knife4jClientConfig.class)
public @interface EnableKnife4jClient {
}
