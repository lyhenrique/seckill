package com.eric.seckill.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.eric.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by yuhaliu on 2017/9/7.
 */
public class RedisDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private JedisPool jedisPool;

    public RedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

    /**
     * 从redis中拿seckill对象
     *
     * @param seckillId
     * @return
     */
    public Seckill getSeckill(long seckillId) {
        //redis操作逻辑

        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:" + seckillId;
                //Jedis或Redis并没有实现内部序列化操作
                //get-》byte[]-》反序列化-》Object（Seckill）
                //采用自定义序列化方式protobuff protostuff
                //protostuff : pojo.
                byte[] bytes = jedis.get(key.getBytes());
                //缓存获取到了
                if (bytes != null) {
                    //用protostuff把字节数组转化成对象
                    //通过schema new一个空对象传入
                    Seckill seckill = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
                    //此时Seckill对象已经被反序列
                    return seckill;
                }

            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 如果seckill没有缓存，则从db中拿到后put到缓存
     *
     * @param seckill
     * @return
     */
    public String putSeckill(Seckill seckill) {
        // set Object(Seckill) -> 序列化 -> byte[]

        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:" + seckill.getSeckillId();
                //第三个参数是缓存器
                byte[] bytes = ProtostuffIOUtil.toByteArray(seckill,schema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                //超时缓存
                int timeout = 60 * 60; //单位是秒
                String result = jedis.setex(key.getBytes(), timeout, bytes);
                return result;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
