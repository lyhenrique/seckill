package com.eric.seckill.exception;

/**
 * 秒杀关闭异常
 * Created by yuhaliu on 2017/9/4.
 */
public class SeckillCloseException extends SeckillException{

    public SeckillCloseException(String s) {
        super(s);
    }

    public SeckillCloseException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
