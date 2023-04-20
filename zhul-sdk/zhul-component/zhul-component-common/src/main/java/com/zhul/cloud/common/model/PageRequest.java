package com.zhul.cloud.common.model;

import java.io.Serializable;

public class PageRequest implements Serializable {

	private static final long serialVersionUID = 8357212825359633398L;

	private Integer pageNum = 1;

	private Integer pageSize = 10;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
