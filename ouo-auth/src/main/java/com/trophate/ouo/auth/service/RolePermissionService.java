package com.trophate.ouo.auth.service;

import com.trophate.ouo.auth.entity.RolePermission;
import com.trophate.ouo.auth.repository.RolePermissionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RolePermissionService {

    private final RolePermissionRepository rolePermissionRepository;

    public RolePermissionService(RolePermissionRepository rolePermissionRepository) {
        this.rolePermissionRepository = rolePermissionRepository;
    }

    public void saveAll(List<RolePermission> rolePermissions) {
        rolePermissionRepository.saveAll(rolePermissions);
    }

    public void deleteByRoleId(int roleId) {
        rolePermissionRepository.deleteByRoleId(roleId);
    }

    public List<Integer> getPermissionIdsByRoleIds(List<Integer> roleIds) {
        List<RolePermission> rolePermissions = rolePermissionRepository.findByRoleIdIn(roleIds);
        var permissionIds = new ArrayList<Integer>();
        for (RolePermission rolePermission : rolePermissions) {
            permissionIds.add(rolePermission.getPermissionId());
        }
        return permissionIds;
    }
}
