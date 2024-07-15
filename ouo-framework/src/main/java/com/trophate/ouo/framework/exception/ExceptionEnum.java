package com.trophate.ouo.framework.exception;

public enum ExceptionEnum {

    /**
     * 未登录
     */
    NO_LOGIN(275001, "未登录"),
    /**
     * 无效的用户名或密码
     */
    INVALID_USERNAME_OR_PASSWORD(275002, "无效的用户名或密码"),
    /**
     * 用户名已存在
     */
    USERNAME_EXIST(275030, "用户名已存在");

    private final int code;
    private final String message;

    ExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ExceptionEnum getByCode(int code) {
        for (ExceptionEnum val : values()) {
            if (val.code == code) {
                return val;
            }
        }
        return null;
    }
}
