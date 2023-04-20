package com.zhul.cloud.examples.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * Created by yanglikai on 2020/12/9.
 */
@Data
@TableName(value = "my_user")
public class UserDO {

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  private String name;

  private String mobile;

  private Date createTime;

  private String createBy;

  private Date updateTime;

  private String updateBy;
}
