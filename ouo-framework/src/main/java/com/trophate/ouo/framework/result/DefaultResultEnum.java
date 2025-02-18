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
    private final String value;

    DefaultResultEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return value;
    }
}
