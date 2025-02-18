package com.trophate.ouo.framework.result;

import com.trophate.ouo.framework.exception.ResultException;

public class Result {

    /**
     * 状态码
     */
    private int code;
    /**
     * 消息
     */
    private String message;
    /**
     * 数据
     */
    private Object data;

    public int getCode() {
        return code;
    }

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    /**
     * 该方法返回一个成功的Result对象。
     *
     * @return Result
     */
    public static Result success() {
        return new Result().setCode(DefaultResultEnum.SUCCESS.getCode()).setMessage(DefaultResultEnum.SUCCESS.getMessage());
    }

    /**
     * 该方法返回一个失败的Result对象。
     *
     * @return Result
     */
    public static Result fail() {
        return new Result().setCode(DefaultResultEnum.FAIL.getCode()).setMessage(DefaultResultEnum.FAIL.getMessage());
    }

    /**
     * 该方法返回一个失败的Result对象。
     *
     * @param e 异常枚举
     * @return Result
     */
    public static Result fail(ResultException e) {
        return new Result().setCode(e.getCode()).setMessage(e.getMessage());
    }
}
