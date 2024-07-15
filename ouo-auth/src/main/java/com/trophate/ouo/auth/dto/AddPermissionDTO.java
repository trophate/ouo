package com.trophate.ouo.auth.dto;

import java.util.List;

public class AddPermissionDTO {

    /**
     * 权限id集合
     */
    private List<Integer> permissionIds;

    public List<Integer> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Integer> permissionIds) {
        this.permissionIds = permissionIds;
    }
}
