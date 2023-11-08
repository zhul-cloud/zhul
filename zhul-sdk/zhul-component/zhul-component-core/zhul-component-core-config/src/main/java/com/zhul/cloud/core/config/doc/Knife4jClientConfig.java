package com.zhul.cloud.core.config.doc;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * TODO
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/1/6
 */

@Configuration
@EnableSwagger2WebMvc
@Import(BeanValidatorPluginsConfiguration.class)
public class Knife4jClientConfig {

    @Autowired
    private OpenApiExtensionResolver openApiExtensionResolver;


    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${zhul.cloud.scan.basePackages:com.zhul}")
    private String basePackages;

    @Bean(value = "restApi")
    @Order(value = 1)
    public Docket groupRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(groupApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackages))
                .paths(PathSelectors.any())
                .build().extensions(openApiExtensionResolver.buildExtensions(""));
    }


    private ApiInfo groupApiInfo() {
        return new ApiInfoBuilder()
                .title(applicationName + "服务API")
                .build();
    }


}
