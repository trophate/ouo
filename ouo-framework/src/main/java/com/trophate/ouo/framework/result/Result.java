package com.trophate.ouo.framework.result;

import com.trophate.ouo.framework.exception.ResException;

public class Result {

    private int code;
    private String message;
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
     * 成功
     *
     * @return Result
     */
    public static Result success() {
        return new Result().setCode(DefaultResultEnum.SUCCESS.getCode()).setMessage(DefaultResultEnum.SUCCESS.getMessage());
    }

    /**
     * 失败
     *
     * @return Result
     */
    public static Result fail() {
        return new Result().setCode(DefaultResultEnum.FAIL.getCode()).setMessage(DefaultResultEnum.FAIL.getMessage());
    }

    /**
     * 失败
     *
     * @return Result
     */
    public static Result fail(ResException e) {
        return new Result().setCode(e.getCode()).setMessage(e.getMessage());
    }
}
