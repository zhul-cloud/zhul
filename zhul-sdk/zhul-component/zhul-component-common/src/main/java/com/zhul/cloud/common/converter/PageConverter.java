package com.zhul.cloud.common.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhul.cloud.common.model.PageResponse;
import java.util.List;

/**
 * Created by yanglikai on 2019/5/28.
 */
public abstract class PageConverter<T, R> implements Converter<Page<T>, PageResponse<R>> {

  @Override
  public PageResponse<R> convert(Page<T> source) {
    PageResponse response = new PageResponse();
    response.setTotal(source.getTotal());
    response.setSize(source.getSize());
    response.setCurrent(source.getCurrent());
    response.setPages(source.getPages());
    response.setList(tList(source.getRecords()));

    return response;
  }

  public abstract List<R> tList(List<T> source);
}
