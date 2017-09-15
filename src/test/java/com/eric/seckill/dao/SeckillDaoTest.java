package com.eric.seckill.dao;

import com.eric.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 配置spring和junit整合,junit启动是加在Spring IOC容器
 * spring-test,junit
 * Created by yuhaliu on 2017/9/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉Junit spring配置文件位置
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    //注入Dao实现类依赖
    @Resource
    private SeckillDao seckillDao;


    @Test
    /**
     * 1000元秒杀iphone7p
     Seckill
     {seckillId=1000,
     name='1000元秒杀iphone7p',
     number=100,
     startTime=Mon Sep 04 00:00:00 CST 2017,
     endTime=Tue Sep 05 00:00:00 CST 2017,
     createTime=Sun Sep 03 16:57:37 CST 2017}
     */
    public void queryById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);

    }

    @Test
    /**
     * Caused by: org.apache.ibatis.binding.BindingException:
     * Parameter 'offset' not found. Available parameters are [0, 1, param1, param2]
     */
    // List<Seckill> queryAll(int offset, int limit);
    // java没有保存形参的记录queryAll(int offset, int limit) -》 queryAll(arg0,arg1)
    // 需要在接口的参数上加入@Param("offset")这样的注解
    public void queryAll() throws Exception {
        List<Seckill> seckills = seckillDao.queryAll(0,100);
        for(Seckill seckill : seckills) {
            System.out.println(seckill);
        }
    }

    @Test
    /**
     update seckill
     SET number = number - 1
     WHERE seckill_id = ?
     AND start_time <= ?
     AND end_time >= ? AND number > 0;
     1000(Long), 2017-09-04 14:15:10.36(Timestamp), 2017-09-04 14:15:10.36(Timestamp)
     14:15:10.895 [main] DEBUG c.e.s.dao.SeckillDao.reduceNumber - <==    Updates: 1
     14:15:10.895 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@70d2e40b]
     */
    public void reduceNumber() throws Exception {
        Date killTime = new Date();
        int updateCount = seckillDao.reduceNumber(1000l,killTime);
        System.out.println("updateCount=" + updateCount);
    }

}