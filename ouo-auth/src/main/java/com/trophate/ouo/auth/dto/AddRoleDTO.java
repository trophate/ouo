package com.trophate.ouo.auth.dto;

import java.util.List;

public class AddRoleDTO {

    /**
     * 角色id集合
     */
    private List<Integer> roleIds;

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }
}
