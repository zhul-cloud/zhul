package com.zhul.cloud.common.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * Created by yanglikai on 2019/5/28.
 */
public final class MapperPage {

  private Page source;

  private MapperPage(Page source) {
    this.source = source;
  }

  public static MapperPage wrap(Page source) {
    return new MapperPage(source);
  }

  private <E> Page<E> tMap(Class target) {
    return Mapper.map(source, target);
  }
}
