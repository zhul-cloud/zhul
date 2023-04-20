package com.zhul.cloud.common.wrapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhul.cloud.common.model.PageResponse;

/**
 * Created by yanglikai on 2019/5/28.
 */
public final class PageWrapper {

  private PageWrapper() {
  }

  public static PageResponse wrap(Page page) {
    PageResponse response = new PageResponse();
    response.setTotal(page.getTotal());
    response.setSize(page.getSize());
    response.setCurrent(page.getCurrent());
    response.setPages(page.getPages());
    response.setList(page.getRecords());

    return response;
  }
}
