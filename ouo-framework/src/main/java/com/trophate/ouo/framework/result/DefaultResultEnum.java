package com.trophate.ouo.framework.result;

public enum DefaultResultEnum {

    SUCCESS(1, "success"),
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
