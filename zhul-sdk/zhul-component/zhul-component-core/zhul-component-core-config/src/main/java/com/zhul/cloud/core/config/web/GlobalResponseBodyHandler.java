package com.zhul.cloud.core.config.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhul.cloud.common.model.RootObject;
import com.zhul.cloud.common.wrapper.PageWrapper;
import com.zhul.cloud.common.wrapper.WrapMapper;
import java.util.Collection;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Created by yanglikai on 2019/5/28.
 * Modify by zhangzheyuan on 2021/1/6
 */
@RestControllerAdvice
public class GlobalResponseBodyHandler implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType, Class selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {

        if (body instanceof Collection) {
            return WrapMapper.ok(body);
        }

        if (body instanceof Page) {
            return WrapMapper.ok(PageWrapper.wrap((Page) body));
        }

        if (body instanceof RootObject) {
            return WrapMapper.ok(body);
        }

        return body;
    }
}
