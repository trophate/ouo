package com.trophate.ouo.auth.entity;

import com.trophate.ouo.auth.entity.pk.UserRolePK;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@Entity(name = "user_role")
@DynamicInsert
@DynamicUpdate
@IdClass(value = UserRolePK.class)
public class UserRole implements Serializable {

    /**
     * 用户名
     */
    @Id
    private Integer userId;
    /**
     * 密码
     */
    @Id
    private Integer roleId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
