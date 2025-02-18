package com.trophate.ouo.auth.enums;

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
    private final String value;

    SexEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return value;
    }

    public static SexEnum getByCode(int code) {
        for (SexEnum e : values()) {
            if (e.code == code) {
                return e;
            }
        }
        return null;
    }
}
