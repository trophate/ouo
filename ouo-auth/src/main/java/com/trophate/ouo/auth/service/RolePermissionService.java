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

    /**
     * 批量保存。
     *
     * @param rolePermissions 参数集合
     */
    public void saveInBatch(List<RolePermission> rolePermissions) {
        rolePermissionRepository.saveAll(rolePermissions);
    }

    /**
     * 通过角色id删除。
     *
     * @param roleId 角色id
     */
    public void deleteByRoleId(int roleId) {
        rolePermissionRepository.deleteByRoleId(roleId);
    }

    /**
     * 通过角色id集合获取权限id集合。
     *
     * @param roleIds 角色id集合
     * @return List<Integer>
     */
    public List<Integer> getPermissionIdsByRoleIds(List<Integer> roleIds) {
        List<RolePermission> rolePermissions = rolePermissionRepository.findByRoleIdIn(roleIds);
        var permissionIds = new ArrayList<Integer>();
        for (RolePermission rolePermission : rolePermissions) {
            permissionIds.add(rolePermission.getPermissionId());
        }
        return permissionIds;
    }
}
