package org.seckill.dto;

public class SeckillResult<T> {

    private boolean success;

    private T data;

    private String error;

    private SeckillResult() {}

    public static <T> SeckillResult<T> error(String error) {
        SeckillResult<T> result = new SeckillResult<T>();
        result.success = false;
        result.error = error;
        return result;
    }

    public static <T> SeckillResult<T> success(T data) {
        SeckillResult<T> result = new SeckillResult<T>();
        result.success = true;
        result.data = data;
        return result;
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
