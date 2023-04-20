package com.zhul.cloud.common.util.sign;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.HmacAlgorithm;
import com.zhul.cloud.common.enums.SignType;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * 签名工具
 * <p></p>
 * Created by yanglikai on 2021/9/1.
 */
@Slf4j
public final class SignKit {

  public static String md5(String data) {
    return SecureUtil.md5(data);
  }

  public static String hmacSha256(String data, String key) {
    return SecureUtil.hmac(HmacAlgorithm.HmacSHA256, key).digestHex(data, CharsetUtil.UTF_8);
  }

  public static String createSign(Map<String, String> params, String privateKey) {
    return createSign(params, privateKey, SignType.MD5);
  }

  public static String createSign(Map<String, String> params, String privateKey,
      SignType signType) {
    if (signType == null) {
      signType = SignType.MD5;
    }

    String tempStr = createLinkString(params);
    String stringSignTemp = tempStr + "&privateKey=" + privateKey;

    if (log.isDebugEnabled()) {
      log.info("signTemp:" + stringSignTemp);
    }

    if (signType == SignType.MD5) {
      return md5(stringSignTemp).toUpperCase();
    } else {
      return hmacSha256(stringSignTemp, privateKey).toUpperCase();
    }
  }

  public static String createLinkString(Map<String, String> params) {
    return createLinkString(params, false);
  }

  public static String createLinkString(Map<String, String> params, boolean encode) {
    return createLinkString(params, "&", encode);
  }

  public static String createLinkString(Map<String, String> params, String connStr,
      boolean encode) {
    return createLinkString(params, connStr, encode, false);
  }

  public static String createLinkString(Map<String, String> params, String connStr, boolean encode,
      boolean quotes) {
    List<String> keys = new ArrayList<>(params.keySet());
    Collections.sort(keys);
    StringBuilder content = new StringBuilder();
    for (int i = 0; i < keys.size(); i++) {
      String key = keys.get(i);
      String value = params.get(key);
      // 拼接时，不包括最后一个&字符
      if (i == keys.size() - 1) {
        if (quotes) {
          content.append(key).append("=").append('"').append(encode ? urlEncode(value) : value)
              .append('"');
        } else {
          content.append(key).append("=").append(encode ? urlEncode(value) : value);
        }
      } else {
        if (quotes) {
          content.append(key).append("=").append('"').append(encode ? urlEncode(value) : value)
              .append('"').append(connStr);
        } else {
          content.append(key).append("=").append(encode ? urlEncode(value) : value).append(connStr);
        }
      }
    }
    return content.toString();
  }

  public static String urlEncode(String src) {
    try {
      return URLEncoder.encode(src, CharsetUtil.UTF_8).replace("+", "%20");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      return null;
    }
  }
}
