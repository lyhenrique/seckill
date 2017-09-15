package com.eric.seckill.exception;

/**
 * 重复秒杀异常(运行期异常)
 * Created by yuhaliu on 2017/9/4.
 */
public class RepeatKillException extends SeckillException{

    public RepeatKillException(String s) {
        super(s);
    }

    public RepeatKillException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
