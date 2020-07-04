package com.su.shisui.common.result;

import java.io.Serializable;

/**
 * author sly
 */
public class Result<T> implements Serializable {

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回结果描述
     */
    private String message;

    /**
     * 返回具体数据
     */
    private T data;

    /**
     * 返回当前时间（毫秒）
     */
    private long time;

    public Result(ResultStatus status) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.data = null;
    }

    public Result(ResultStatus status, T data) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.data = data;
    }

    public void setResultStatus(ResultStatus status) {
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public Result(int code,String msg, T data){
        this.code = code;
        this.message = msg;
        this.data = data;
    }

    public static<T> Result ok(T data) {
        return new Result(CommonResultStatus.SUCCESS, data);
    }

    public static<T> Result ok() {
        return new Result(CommonResultStatus.SUCCESS);
    }

    public static<T> Result fail() {
        return new Result(CommonResultStatus.ERROR);
    }

    public static<T> Result fail(String msg) {
        return new Result(CommonResultStatus.ERROR.getCode(),msg,null);
    }
    public static<T> Result fail(ResultStatus resultStatus){
        return new Result(resultStatus.getCode(),resultStatus.getMessage(),null);
    }
    public static<T> Result fail(int code,String msg) {
        return new Result(code,msg,null);
    }

    public boolean isSuccessful() {
        return this.code == 0;
    }
    /**
     * 返回码
     */
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 返回结果描述
     */
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 返回具体数据
     */
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTime() {
        return System.currentTimeMillis();
    }

    public void setTime(long time) {
        this.time = time;
    }
}
