package com.trophate.ouo.auth.enums;

public enum PermissionTypeEnum {

    /**
     * 菜单
     */
    ORDER(1, "菜单"),
    /**
     * 按钮
     */
    BUTTON(2, "按钮");

    private final int code;
    private final String message;

    PermissionTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static PermissionTypeEnum getByCode(int code) {
        for (PermissionTypeEnum val : values()) {
            if (val.code == code) {
                return val;
            }
        }
        return null;
    }
}
