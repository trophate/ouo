package com.trophate.ouo.framework.exception;

public enum ExceptionEnum {

    /**
     * 未登录
     */
    NOT_LOGIN(370001, "未登录"),
    /**
     * 无效的用户名或密码
     */
    INVALID_USERNAME_OR_PASSWORD(3700002, "无效的用户名或密码"),
    /**
     * 用户名已存在
     */
    USERNAME_EXIST(3700003, "用户名已存在");

    /**
     * 异常码
     */
    private final int code;
    /**
     * 异常消息
     */
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

    /**
     * 通过异常码获取枚举。
     *
     * @param code 异常码
     * @return ExceptionEnum
     */
    public static ExceptionEnum getByCode(int code) {
        for (ExceptionEnum val : values()) {
            if (val.code == code) {
                return val;
            }
        }
        return null;
    }
}
