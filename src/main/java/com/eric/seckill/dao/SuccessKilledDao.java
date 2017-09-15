package com.eric.seckill.dao;

import com.eric.seckill.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * Created by yuhaliu on 2017/9/3.
 */
public interface SuccessKilledDao {

    /**
     * 插入秒杀成功购买明细，可过滤重复，通过联合主键
     * @param seckillId
     * @param userPhone
     * @return 插入的行数 = 1,表示插入一行成功
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);

    /**
     * 根据id查询SuccessKilled并携带秒杀产品对象实体
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);
}
