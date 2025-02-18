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
    private final String value;

    PermissionTypeEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return value;
    }

    public static PermissionTypeEnum getByCode(int code) {
        for (PermissionTypeEnum e : values()) {
            if (e.code == code) {
                return e;
            }
        }
        return null;
    }
}
