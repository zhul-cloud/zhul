package com.zhul.cloud.common.model;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * Created by yanglikai on 2019/5/28.
 */
@Data
public class PageResponse<T> implements Serializable {

  private long total;

  private long size;

  private long current;

  private long pages;

  private List<T> list;
}
