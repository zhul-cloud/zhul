package com.zhul.cloud.common.wrapper;


import com.zhul.cloud.common.enums.ErrorCode;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by yanglikai on 2019/5/28.
 */
public final class WrapMapper {

    private WrapMapper() {
    }

    public static <E> Wrapper<E> wrap(String code, String message, E target) {
        return new Wrapper<>(code, message, target);
    }

    public static <E> Wrapper<E> wrap(String code, String message) {
        return wrap(code, message, null);
    }

    public static <E> Wrapper<E> wrap(String code) {
        return wrap(code, null);
    }

    public static <E> Wrapper<E> wrap(Exception e) {
        return new Wrapper<>(ErrorCode.FAIL.code(), e.getMessage());
    }

    public static <E> E unWrap(Wrapper<E> wrapper) {
        return wrapper.getData();
    }

    public static <E> Wrapper<E> error() {
        return wrap(ErrorCode.FAIL.code(), ErrorCode.FAIL.msg());
    }

    public static <E> Wrapper<E> error(String message) {
        return wrap(ErrorCode.FAIL.code(), StringUtils.isBlank(message) ? ErrorCode.FAIL.msg() : message);
    }

    public static <E> Wrapper<E> error(ErrorCode errorCode) {
        return wrap(errorCode.code(), errorCode.msg());
    }

    public static <E> Wrapper<E> ok() {
        return new Wrapper<>();
    }

    public static <E> Wrapper<E> ok(E target) {
        return new Wrapper<>(ErrorCode.SUCCESS.code(), ErrorCode.SUCCESS.msg(), target);
    }
}
