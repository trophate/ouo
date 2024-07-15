package com.trophate.ouo.framework.result;

public enum DefaultResultEnum {

    /**
     * 成功
     */
    SUCCESS(1, "success"),
    /**
     * 失败
     */
    FAIL(-1, "fail");

    private final int code;
    private final String message;

    DefaultResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
