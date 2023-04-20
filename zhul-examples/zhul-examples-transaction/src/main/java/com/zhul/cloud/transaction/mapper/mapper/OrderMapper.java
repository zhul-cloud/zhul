package com.zhul.cloud.transaction.mapper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhul.cloud.transaction.mapper.entity.OrderDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by yanglikai on 2021/1/13.
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderDO> {

}
