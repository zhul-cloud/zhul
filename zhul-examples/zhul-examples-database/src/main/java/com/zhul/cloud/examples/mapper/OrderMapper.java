package com.zhul.cloud.examples.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhul.cloud.examples.entity.OrderDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by yanglikai on 2020/12/9.
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderDO> {

  OrderDO selectByCode(String orderCode);
}
