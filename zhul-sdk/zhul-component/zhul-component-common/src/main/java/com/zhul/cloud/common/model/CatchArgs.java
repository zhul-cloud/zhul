package com.zhul.cloud.common.model;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.compress.utils.Lists;

/**
 * Created by yanglikai on 2021/9/3.
 */
@Data
public class CatchArgs implements Serializable {

  private List<CatchArg> args;

  public CatchArgs() {
    this.args = Lists.newArrayList();
  }

  public void add(String type, Object value) {
    this.args.add(new CatchArg(type, value));
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class CatchArg implements Serializable {

    private String type;

    private Object value;
  }
}
