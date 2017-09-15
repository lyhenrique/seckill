package com.eric.seckill.exception;

/**
 * 秒杀相关业务异常
 * Created by yuhaliu on 2017/9/4.
 */
public class SeckillException extends RuntimeException{

    public SeckillException(String s) {
        super(s);
    }

    public SeckillException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
