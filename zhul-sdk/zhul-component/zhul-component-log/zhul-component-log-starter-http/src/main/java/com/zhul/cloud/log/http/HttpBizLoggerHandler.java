package com.zhul.cloud.log.http;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSON;
import com.zhul.cloud.common.logger.BizLoggerHandler;
import com.zhul.cloud.common.logger.BizLoggerMessage;
import com.zhul.cloud.common.logger.OptUser;
import com.zhul.cloud.common.logger.OptUserHandler;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * 日志发往wq-sod-logger服务
 *
 * @author zhangzheyuan
 * @version 1.0
 * @date 2021/9/30
 */
@Slf4j
public class HttpBizLoggerHandler implements BizLoggerHandler {

  private final static okhttp3.MediaType JSON_MEDIA_TYPE = okhttp3.MediaType
      .parse("application/json; charset=utf-8");

  @Value("${spring.application.name:}")
  private String applicationName;
  @Value("${zhul.cloud.log.http.url:}")
  private String url;
  @Value("${zhul.cloud.log.http.method:POST}")
  private String method;
  @Value("${zhul.cloud.log.http.loadBalance:false}")
  private boolean isLoadBalance;
  @Autowired(required = false)
  private OptUserHandler optUserHandler;
  @Autowired
  private RestTemplate restTemplate;

  private static class HttpClient {

    private static OkHttpClient client = new OkHttpClient().newBuilder()
        .connectTimeout(20000, TimeUnit.MILLISECONDS)
        .readTimeout(20000, TimeUnit.MILLISECONDS)
        .writeTimeout(20000, TimeUnit.MILLISECONDS)
        .retryOnConnectionFailure(true)
        .connectionPool(new ConnectionPool()).build();
  }

  private OkHttpClient getHttpClient() {
    return HttpClient.client;
  }

  @Override
  public void persistent(Object message) {

    if (isLoadBalance) {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      restTemplate.getMessageConverters()
          .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

      HttpEntity<Object> entity = new HttpEntity<>(message, headers);

      ResponseEntity<String> response = null;
      response = restTemplate
          .exchange(url, HttpMethod.resolve(method), entity, String.class);
      if (response.getStatusCode().isError()) {
        log.error("业务日志记录失败");
      }
    } else {
      String json = JSON.toJSONString(message);

      RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, json);
      Request request = new Request.Builder().url(url).method(method, body).build();
      try (Response response = getHttpClient().newCall(request).execute()) {
        if (!response.isSuccessful()) {
          log.error("业务日志记录失败");
        }
      } catch (Exception e) {
        log.error(e.toString(), e);
      }
    }


  }

  @Override
  public void persistent(BizLoggerMessage message) {
    this.persistent(message, 3);
  }

  @Override
  public void persistent(BizLoggerMessage message, int stackTraceLevel) {
    Date now = DateTime.now();
    // 应用名
    if (message.getApplicationName() == null) {
      message.setApplicationName(applicationName);
    }
    if (message.getMethod() == null) {
      StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
      if (stackTraceElements != null && stackTraceElements.length > stackTraceLevel) {
        message.setMethod(stackTraceElements[stackTraceLevel].toString());
      }
    }
    // 日志时间
    if (message.getOptTime() == null) {
      message.setOptTime(now);
    }
    // 操作人
    if (optUserHandler != null && message.getOptUserId() == null) {
      OptUser optUser = optUserHandler.generate(null);
      if (optUser != null) {
        message.setOptUserId(optUser.getOptUserId());
        message.setOptUserName(optUser.getOptUserName());
      }
    }

    this.persistent((Object) message);
  }
}
