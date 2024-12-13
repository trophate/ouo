package com.trophate.ouo.framework.exception;

public class ResultException extends BaseException {

    /**
     * 异常码
     */
    private int code = -1;

    public ResultException() {}

    public ResultException(String message) {
        super(message);
    }

    public ResultException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ResultException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
    }

    public int getCode() {
        return code;
    }
}
