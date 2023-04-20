package com.zhul.cloud.common.model;

import java.io.Serializable;
import java.util.List;
import lombok.Data;
import org.apache.commons.compress.utils.Lists;

/**
 * Antd分页组件
 * <p></p>
 * Created by yanglikai on 2022/12/15.
 */
@Data
public class AntdPage<T> implements Serializable {

  private long total;

  private long pageSize;

  private long current;

  private List<T> data;

  public AntdPage(long total, long pageSize, long current) {
    this.total = total;
    this.pageSize = pageSize;
    this.current = current;
    this.data = Lists.newArrayList();
  }

  public AntdPage() {
  }
}
