package com.eric.seckill.dao;

import com.eric.seckill.entity.Seckill;
import com.eric.seckill.entity.SuccessKilled;
import com.sun.net.httpserver.Authenticator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by yuhaliu on 2017/9/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    /**
     * 第一次insertCount=1
     * 第二次insertCount=0
     */
    public void insertSuccessKilled() throws Exception {
        long id = 1001L;
        long userPhone = 13794496963L;
        int insertCount = successKilledDao.insertSuccessKilled(id,userPhone);
        System.out.println("insertCount" + insertCount);

    }

    @Test
    /**
     * SuccessKilled
     * {seckillId=1000,
     * userPhone=13794496963,
     * state=-1,
     * createTime=Mon Sep 04 15:32:11 CST 2017}
     Seckill
     {seckillId=1000,
     name='1000元秒杀iphone7p',
     number=100,
     startTime=Tue Sep 05 00:00:00 CST 2017,
     endTime=Wed Sep 06 00:00:00 CST 2017,
     createTime=Sun Sep 03 16:57:37 CST 2017}
     */
    public void queryByIdWithSeckill() throws Exception {
        long id = 1000L;
        long userPhone = 13794496963L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id,userPhone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }

}