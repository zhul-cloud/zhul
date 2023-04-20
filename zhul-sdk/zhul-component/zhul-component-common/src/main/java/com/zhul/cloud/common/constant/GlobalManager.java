package com.zhul.cloud.common.constant;


/**
 * Created by yanglikai on 2019/5/28.
 */
public final class GlobalManager {
  public static final String UNKNOWN = "unknown";

  public static final String X_FORWARDED_FOR = "X-Forwarded-For";
  public static final String X_REAL_IP = "X-Real-IP";
  public static final String PROXY_CLIENT_IP = "Proxy-Client-IP";
  public static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
  public static final String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
  public static final String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";

  public static final String LOCALHOST_IP_16 = "0:0:0:0:0:0:0:1";
  public static final int MAX_IP_LENGTH = 15;

  public static final int DEFAULT_PAGE = 1;
  public static final int DEFAULT_SIZE = 10;
//  public static final int DEFAULT_PAGE_SIZE = 10;

  public static final String VERSION = "1.0.0";

  private GlobalManager() {
  }

  /**
   * 系统常量
   */
  public static final class System {
    private System() {
    }

    public static final String TOKEN_AUTH = "CURRENT_USER";
  }

  /**
   * 符号
   */
  public static final class Symbol {
    private Symbol() {
    }

    public static final String COMMA = ",";

    public static final String SPOT = ".";

    public static final String UNDER_LINE = "_";

    public static final String PER_CENT = "%";

    public static final String AT = "@";

    public static final String PIPE = "||";

    public static final String SHORT_LINE = "-";

    public static final String SPACE = " ";

    public static final String SLASH = "/";

    public static final String MH = ":";
  }
}
