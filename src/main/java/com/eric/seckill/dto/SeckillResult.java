package com.eric.seckill.dto;

/**
 * Created by yuhaliu on 2017/9/5.
 */
//所有ajax的返回类型，封装JSON结果
public class SeckillResult<T> {

    /**
     * 成功与否
     */
    private boolean success;

    /**
     * 泛型类型数据
     */
    private T data;

    /**
     * 错误信息
     */
    private String error;

    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
