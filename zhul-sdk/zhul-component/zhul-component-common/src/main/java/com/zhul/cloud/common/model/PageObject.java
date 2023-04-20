package com.zhul.cloud.common.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhul.cloud.common.constant.GlobalManager;
import lombok.Data;

/**
 * Created by yanglikai on 2019/5/28.
 */
@Data
public abstract class PageObject extends RootObject {

  @ExcelIgnore
  @TableField(exist = false)
  private Integer page;

  @ExcelIgnore
  @TableField(exist = false)
  private Integer size;

  public int current() {
    return page == null ? GlobalManager.DEFAULT_PAGE : page;
  }

  public int size() {
    return size == null ? GlobalManager.DEFAULT_SIZE : size;
  }

  @JsonIgnore
  public int getLimit() {
    return size();
  }

  @JsonIgnore
  public int getOffset() {
    return (current() - 1) * size();
  }

  public Page tPage() {
    return new Page<>(current(), size());
  }
}
