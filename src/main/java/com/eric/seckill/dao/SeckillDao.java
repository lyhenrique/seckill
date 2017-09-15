package com.eric.seckill.dao;

import com.eric.seckill.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by yuhaliu on 2017/9/3.
 */
public interface SeckillDao {

    /**
     * 减库存，
     * @param seckillId
     * @param killTime 对应数据库中的createTime
     * @return 如果影响长度 > 1,表示更新的记录行数
     */
    int reduceNumber(@Param("seckillId") long seckillId,@Param("killTime") Date killTime);

    /**
     * 根据id查询秒杀对象
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 查询需要的秒杀商品列表。
     * @param offset   从第几条开始
     * @param limit    查询几条
     * @return
     */
    List<Seckill> queryAll(@Param("offset") int offset,@Param("limit") int limit);

    /**
     * 使用存储过程执行秒杀
     * @param paramMap
     */
    void killByProcedure(Map<String, Object> paramMap);
}
