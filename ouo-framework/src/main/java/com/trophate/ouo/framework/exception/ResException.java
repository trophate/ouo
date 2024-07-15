package com.trophate.ouo.framework.exception;

public class ResException extends BaseException {

    /**
     * 异常码
     */
    private int code = -1;

    public ResException() {

    }

    public ResException(String message) {
        super(message);
    }

    public ResException(String message, int code) {
        super(message);
        this.code = code;
    }

    public ResException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
    }

    public int getCode() {
        return code;
    }
}
