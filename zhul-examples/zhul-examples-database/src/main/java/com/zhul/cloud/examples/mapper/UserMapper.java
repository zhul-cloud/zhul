package com.zhul.cloud.examples.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhul.cloud.examples.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by yanglikai on 2020/12/9.
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO>{
}
