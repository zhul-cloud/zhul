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
@TableName(value = "biz_order_tbl")
public class BizOrderDO implements Serializable {

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  private String userId;

  private String productCode;

  private Integer count;

  private Integer money;
}
