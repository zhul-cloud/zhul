package com.zhul.cloud.examples.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhul.cloud.common.model.GeneralDO;
import lombok.Data;

/**
 * Created by yanglikai on 2020/12/9.
 */
@Data
@TableName(value = "t_order")
public class OrderDO extends GeneralDO {

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  @TableField(value = "order_code")
  private Integer orderCode;

  @TableField(value = "warehouse_code")
  private String warehouseCode;
}
