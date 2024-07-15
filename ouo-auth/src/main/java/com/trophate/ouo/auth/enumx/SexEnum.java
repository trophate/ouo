package com.trophate.ouo.auth.enumx;

public enum SexEnum {

    /**
     * 男
     */
    MAN(1, "男"),
    /**
     * 女
     */
    WOMEN(2, "女");

    private final int code;
    private final String message;

    SexEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static SexEnum getByCode(int code) {
        for (SexEnum val : values()) {
            if (val.code == code) {
                return val;
            }
        }
        return null;
    }
}
