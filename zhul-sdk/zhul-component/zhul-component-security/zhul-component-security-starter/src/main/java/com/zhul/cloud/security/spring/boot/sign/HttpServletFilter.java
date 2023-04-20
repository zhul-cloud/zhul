package com.zhul.cloud.security.spring.boot.sign;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by yanglikai on 2021/9/1.
 */
public class HttpServletFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    ServletRequest requestWrapper = null;

    if (request instanceof HttpServletRequest) {
      requestWrapper = new RequestWrapper((HttpServletRequest) request);
    }

    if (requestWrapper == null) {
      chain.doFilter(request, response);
    } else {
      chain.doFilter(requestWrapper, response);
    }
  }
}
