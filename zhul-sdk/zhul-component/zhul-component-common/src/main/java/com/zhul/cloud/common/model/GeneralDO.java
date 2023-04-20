package com.zhul.cloud.common.model;

import java.util.Date;
import lombok.Data;

/**
 * Created by yanglikai on 2019/5/28.
 */
@Data
public abstract class GeneralDO extends GeneralObject {

  private Date createTime; // 创建时间

  private String createBy; // 创建者

  private Date updateTime; // 更新时间

  private String updateBy; // 更新者

  public <T> T tResponse(Class<? extends GeneralObject> target) {
    return map(target);
  }

  public static final String CREATE_TIME = "CREATE_TIME";
  public static final String CREATE_BY = "CREATE_BY";
  public static final String UPDATE_TIME = "UPDATE_TIME";
  public static final String UPDATE_BY = "UPDATE_BY";


}
