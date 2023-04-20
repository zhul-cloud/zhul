package com.zhul.cloud.security.spring.boot;

import com.zhul.cloud.security.spring.boot.sign.SignatureAuthorizationAutoConfiguration;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

/**
 * Created by yanglikai on 2021/9/1.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(SignatureAuthorizationAutoConfiguration.class)
public @interface EnableSecurityClient {

}
