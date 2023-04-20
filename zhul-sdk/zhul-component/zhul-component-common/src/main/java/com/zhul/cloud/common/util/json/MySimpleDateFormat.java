package com.zhul.cloud.common.util.json;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yanglikai on 2019/5/23.
 */
public class MySimpleDateFormat extends SimpleDateFormat {
  public MySimpleDateFormat(String pattern) {
    super(pattern);
  }

  @Override
  public Date parse(String source) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    try {
      return sdf.parse(source);
    } catch (Exception e) {
      Long ts = Long.valueOf(Long.parseLong(source));
      return new Date(ts.longValue());
    }
  }
}
