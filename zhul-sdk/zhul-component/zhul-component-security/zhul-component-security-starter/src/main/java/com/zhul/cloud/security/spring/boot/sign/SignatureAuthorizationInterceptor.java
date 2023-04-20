package com.zhul.cloud.security.spring.boot.sign;

import cn.hutool.http.ContentType;
import com.zhul.cloud.common.enums.ErrorCode;
import com.zhul.cloud.common.exception.BizException;
import com.zhul.cloud.common.util.sign.SignKit;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Created by yanglikai on 2021/9/1.
 */
public class SignatureAuthorizationInterceptor implements HandlerInterceptor {

  private static final String SIGN_HEADER = "x-wq-client-sign";

  private String privateKey;

  private String[] interceptPath;

  public SignatureAuthorizationInterceptor(String privateKey) {
    this.privateKey = privateKey;
  }

  public SignatureAuthorizationInterceptor(String privateKey, String[] interceptPath) {
    this.privateKey = privateKey;
    this.interceptPath = interceptPath;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    String originalSign = request.getHeader(SIGN_HEADER);

    String method = request.getMethod();
    if ("POST".equalsIgnoreCase(method)
        && request.getContentType().contains(ContentType.JSON.getValue())) {
      /* path匹配 */
      String path = request.getServletPath();
      AntPathMatcher antPathMatcher = new AntPathMatcher();
      if (this.interceptPath != null) {
        for (String target : this.interceptPath) {
          boolean matched = antPathMatcher.match(target, path);
          if (matched) {
            if (StringUtils.isEmpty(originalSign)) {
              throw new BizException(ErrorCode.A0340);
            }
          }
        }
      }

      if (StringUtils.isEmpty(originalSign)) {
        return true;
      }

      // 此处读取了request中的inputStream，因为只能被读取一次，后面spring框架无法读取了，所以需要添加wrapper和filter解决流只能读取一次的问题
      BufferedReader reader = request.getReader();
      if (reader == null) {
        return false;
      }

      String data = readData(request);
      Map<String, String> params = new HashMap<>();
      params.put("data", data);

      if (StringUtils.isEmpty(this.privateKey)) {
        throw new BizException(ErrorCode.A0415);
      }

      String sign = SignKit.createSign(params, this.privateKey);

      // 验证签名是否匹配
      boolean verify = originalSign.equals(sign);
      if (!verify) {
        throw new BizException(ErrorCode.A0340);
      }
    }

    return true;
  }

  private String readData(HttpServletRequest request) {
    BufferedReader br = null;
    try {
      StringBuilder result = new StringBuilder();
      br = request.getReader();
      for (String line; (line = br.readLine()) != null; ) {
        if (result.length() > 0) {
          result.append("\n");
        }
        result.append(line);
      }
      return result.toString();
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}