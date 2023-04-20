package com.zhul.cloud.transaction.mapper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * Created by yanglikai on 2021/1/13.
 */
@Data
@TableName(value = "integral_tbl")
public class IntegralDO implements Serializable {

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  private String userId;

  private Integer money;

  private Integer frozenMoney;
}
