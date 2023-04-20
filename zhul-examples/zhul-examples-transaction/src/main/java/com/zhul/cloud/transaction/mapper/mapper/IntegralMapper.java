package com.zhul.cloud.transaction.mapper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhul.cloud.transaction.mapper.entity.IntegralDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * Created by yanglikai on 2021/1/13.
 */
@Mapper
public interface IntegralMapper extends BaseMapper<IntegralDO> {

  /**
   * 增加积分
   */
  @Update(value = "update integral_tbl set money = money + #{money} where user_id = #{userId}")
  int addIntegral(@Param(value = "userId") String userId, @Param(value = "money") int money);

  /**
   * 冻结积分
   */
  @Update(value = "update integral_tbl set frozen_money = frozen_money + #{money} where user_id = #{userId}")
  int frozenIntegral(@Param(value = "userId") String userId, @Param(value = "money") int money);

  /**
   * 取消冻结
   */
  @Update(value = "update integral_tbl set frozen_money = frozen_money - #{money} where user_id = #{userId}")
  int cancelFrozen(@Param(value = "userId") String userId, @Param(value = "money") int money);
}
