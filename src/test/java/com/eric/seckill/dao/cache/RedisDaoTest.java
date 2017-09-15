package com.eric.seckill.dao.cache;

import com.eric.seckill.dao.SeckillDao;
import com.eric.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by yuhaliu on 2017/9/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉Junit spring配置文件位置
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {
    long id = 1001L;

    @Autowired
    private RedisDao redisDao;

    @Resource
    private SeckillDao seckillDao;

    @Test
    public void testSeckill() throws Exception {
        //get and put
        Seckill seckill = redisDao.getSeckill(id);
        if(seckill == null) {
            seckill = seckillDao.queryById(id);
            if(seckill != null) {
                String result = redisDao.putSeckill(seckill);
                System.out.println(result);
                seckill = redisDao.getSeckill(id);
                System.out.println(seckill);
            }
        }

    }

}