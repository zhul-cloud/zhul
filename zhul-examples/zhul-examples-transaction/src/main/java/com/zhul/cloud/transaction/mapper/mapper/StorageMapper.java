package com.zhul.cloud.transaction.mapper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhul.cloud.transaction.mapper.entity.StorageDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * Created by yanglikai on 2021/1/13.
 */
@Mapper
public interface StorageMapper extends BaseMapper<StorageDO> {

  /**
   * 扣减库存
   */
  @Update(value = "update storage_tbl set count = count - #{count} where product_code = #{productCode} and count >= #{count}")
  int reduceStock(@Param(value = "productCode") String productCode,
      @Param(value = "count") int count);

  /**
   * 冻结库存
   */
  @Update(value = "update storage_tbl set frozen_count = frozen_count + #{count} where product_code = #{productCode}")
  int frozenStock(@Param(value = "productCode") String productCode,
      @Param(value = "count") int count);

  /**
   * 取消冻结
   */
  @Update(value = "update storage_tbl set frozen_count = frozen_count - #{count} where product_code = #{productCode}")
  int cancelFrozen(@Param(value = "productCode") String productCode,
      @Param(value = "count") int count);
}
