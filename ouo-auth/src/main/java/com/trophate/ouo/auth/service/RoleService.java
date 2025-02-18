package com.trophate.ouo.auth.service;

import com.trophate.ouo.auth.dto.AddPermissionDTO;
import com.trophate.ouo.auth.dto.CreateRoleDTO;
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
     * 该方法创建一个角色。
     *
     * @param dto 参数
     */
    public void create(CreateRoleDTO dto) {
        var role = new Role();
        role.setName(dto.getName());
        roleRepository.save(role);
    }

    /**
     * 该方法编辑指定角色。
     *
     * @param id id
     * @param dto 参数
     */
    public void edit(int id, CreateRoleDTO dto) {

    }

    /**
     * 该方法删除指定角色。
     *
     * @param id id
     */
    public void del(int id) {

    }

    /**
     * 该方法为指定角色批量添加权限。
     *
     * @param id id
     * @param dto 参数
     */
    public void addPermissions(int id, AddPermissionDTO dto) {
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
