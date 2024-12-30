package com.trophate.ouo.auth.entity;

import com.trophate.ouo.auth.entity.pk.RolePermissionPK;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@Entity(name = "role_permission")
@DynamicInsert
@DynamicUpdate
@IdClass(value = RolePermissionPK.class)
public class RolePermission implements Serializable {

    /**
     * 角色id
     */
    @Id
    private Integer roleId;
    /**
     * 权限id
     */
    @Id
    private Integer permissionId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
}
