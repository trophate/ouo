package com.trophate.ouo.auth.repository;

import com.trophate.ouo.auth.entity.RolePermission;
import com.trophate.ouo.framework.jpa.CustomRepository;

import java.util.List;

public interface RolePermissionRepository extends CustomRepository<RolePermission, Integer> {

    void deleteByRoleId(int userId);

    List<RolePermission> findByRoleIdIn(List<Integer> roleId);
}
