package com.zhul.cloud.core.config.web;

import com.netflix.client.ClientException;
import com.zhul.cloud.common.enums.ErrorCode;
import com.zhul.cloud.common.exception.BizException;
import com.zhul.cloud.common.wrapper.WrapMapper;
import com.zhul.cloud.common.wrapper.Wrapper;
import feign.FeignException;
import java.sql.SQLException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Created by yanglikai on 2019/5/28.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  /**
   * 参数非法异常.
   */
  @ExceptionHandler({MissingServletRequestParameterException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public Wrapper missingServletRequestParameterExceptionHandle(
      MissingServletRequestParameterException ex) {
    log.error(ex.toString(), ex);
    return WrapMapper.error(ErrorCode.A0400);
  }

  /**
   * 参数非法异常.
   *
   * @param e the e
   * @return the wrapper
   */
  @ExceptionHandler({IllegalArgumentException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public Wrapper illegalArgumentException(IllegalArgumentException e) {
    log.error(e.toString(), e);
    return WrapMapper.wrap(ErrorCode.A0400.code(), e.getLocalizedMessage());
  }

  /**
   * 参数验证异常
   */
  @ExceptionHandler({UnexpectedTypeException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public Wrapper unexpectedTypeException(UnexpectedTypeException e) {
    log.error(e.toString(), e);
    return WrapMapper.wrap(ErrorCode.A0400.code(), e.getLocalizedMessage());
  }

  /**
   * 参数验证异常.
   *
   * @param e the e
   * @return the wrapper
   */
  @ExceptionHandler({MethodArgumentNotValidException.class})
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Wrapper methodArgumentNotValidException(MethodArgumentNotValidException e) {
    log.error(e.toString(), e);

    BindingResult result = e.getBindingResult();
    for (ObjectError error : result.getAllErrors()) {
      return WrapMapper.wrap(ErrorCode.A0400.code(), error.getDefaultMessage());
    }

    return WrapMapper.error(ErrorCode.A0400);
  }

  /**
   * 参数验证异常
   */
  @ExceptionHandler({ConstraintViolationException.class})
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Wrapper constraintViolationException(ConstraintViolationException e) {
    log.error(e.toString(), e);

    for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
      return WrapMapper.wrap(ErrorCode.A0400.code(), constraintViolation.getMessage());// NOSONAR
    }

    return WrapMapper.error(ErrorCode.A0400);
  }

  /**
   * 参数验证异常
   */
  @ExceptionHandler({BindException.class})
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Wrapper bindException(BindException e) {
    log.error(e.toString(), e);

    BindingResult result = e.getBindingResult();
    for (ObjectError error : result.getAllErrors()) {
      return WrapMapper.wrap(ErrorCode.A0400.code(), error.getDefaultMessage());// NOSONAR
    }

    return WrapMapper.error(ErrorCode.A0400);
  }

  /**
   * 参数验证异常
   */
  @ExceptionHandler({HttpMessageNotReadableException.class})
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Wrapper bindException(HttpMessageNotReadableException e) {
    log.error(e.toString(), e);
    return WrapMapper.error(ErrorCode.A0400);
  }

  /**
   * 无访问权限异常.
   *
   * @param e the e
   * @return the wrapper
   */
  @ExceptionHandler({AccessDeniedException.class})
  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ResponseBody
  public Wrapper unAuthorizedException(AccessDeniedException e) {
    log.error(e.toString(), e);

    return WrapMapper.error(ErrorCode.A0312);
  }

  /**
   * 无效认证异常.
   *
   * @param e the e
   * @return the wrapper
   */
  @ExceptionHandler({AuthenticationException.class})
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ResponseBody
  public Wrapper dadCredentialsExceptionException(AuthenticationException e) {
    log.error(e.toString(), e);
    return WrapMapper.error(ErrorCode.A0350);
  }

  /**
   * 找不到指定资源异常.
   */
  @ExceptionHandler(NoHandlerFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public Wrapper noHandlerFoundException(NoHandlerFoundException e) {
    log.error(e.toString(), e);

    return WrapMapper.error(ErrorCode.A0600);
  }

  /**
   * 业务异常.
   *
   * @param e the e
   * @return the wrapper
   */
  @ExceptionHandler(BizException.class)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Wrapper businessException(BizException e) {
    log.warn(e.toString(), e);

    return WrapMapper
        .wrap(e.getCode() == null ? ErrorCode.FAIL.code() : e.getCode(), e.getMessage());
  }

  /**
   * 数据库操作异常.
   *
   * @param e the e
   * @return the wrapper
   */
  @ExceptionHandler(SQLException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public Wrapper sqlException(SQLException e) {
    log.error(e.toString(), e);

    e.printStackTrace();

    return WrapMapper.error(ErrorCode.C0300);
  }

  /**
   * 数据主键重复.
   *
   * @param e the e
   * @return the wrapper
   */
  @ExceptionHandler(DuplicateKeyException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public Wrapper sqlException(DuplicateKeyException e) {
    log.error(e.toString(), e);
    return WrapMapper.error(ErrorCode.C0341);
  }

  /**
   * RPC服务未知异常.
   *
   * @param e the e
   * @return the wrapper
   */
  @ExceptionHandler(FeignException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public Wrapper rpcException(FeignException e) {
    log.error(e.toString(), e);

    e.printStackTrace();

    return WrapMapper.error(ErrorCode.C0110);
  }

  /**
   * RPC 服务未注册.
   *
   * @param e
   * @return
   */
  @ExceptionHandler(ClientException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public Wrapper clientException(ClientException e) {
    log.error(e.toString(), e);

    e.printStackTrace();

    return WrapMapper.error(ErrorCode.C0112);
  }

  /**
   * 未知异常.
   *
   * @param e the e
   * @return the wrapper
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public Wrapper exception(Exception e) {
    log.error(e.toString(), e);

    e.printStackTrace();

    return WrapMapper.error(ErrorCode.FAIL);
  }
}
