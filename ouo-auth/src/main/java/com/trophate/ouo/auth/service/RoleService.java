package com.trophate.ouo.auth.service;

import com.trophate.ouo.auth.dto.AddPermissionDTO;
import com.trophate.ouo.auth.dto.RoleCreateDTO;
import com.trophate.ouo.auth.entity.Role;
import com.trophate.ouo.auth.entity.RolePermission;
import com.trophate.ouo.auth.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final  RolePermissionService rolePermissionService;

    public RoleService(RoleRepository roleRepository, RolePermissionService rolePermissionService) {
        this.roleRepository = roleRepository;
        this.rolePermissionService = rolePermissionService;
    }

    /**
     * 创建。
     *
     * @param dto 参数
     */
    public void create(RoleCreateDTO dto) {
        var role = new Role();
        role.setName(dto.getName());
        roleRepository.save(role);
    }

    /**
     * 添加权限。
     *
     * @param id id
     * @param dto 参数
     */
    public void addPermission(int id, AddPermissionDTO dto) {
        rolePermissionService.deleteByRoleId(id);
        var rolePermissions = new ArrayList<RolePermission>();
        for (int permissionId : dto.getPermissionIds()) {
            var rolePermission = new RolePermission();
            rolePermission.setRoleId(id);
            rolePermission.setPermissionId(permissionId);
            rolePermissions.add(rolePermission);
        }
        rolePermissionService.saveInBatch(rolePermissions);
    }
}
