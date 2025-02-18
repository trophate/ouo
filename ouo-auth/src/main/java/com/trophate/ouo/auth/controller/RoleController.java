package com.trophate.ouo.auth.controller;

import com.trophate.ouo.auth.dto.AddPermissionDTO;
import com.trophate.ouo.auth.dto.CreatePermissionDTO;
import com.trophate.ouo.auth.dto.CreateRoleDTO;
import com.trophate.ouo.auth.service.RoleService;
import com.trophate.ouo.framework.result.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 该方法创建一个角色。
     *
     * @param dto 参数
     * @return Result
     */
    @PostMapping("/create_role")
    @PreAuthorize("hasAuthority('role:create')")
    public Result create(@RequestBody CreateRoleDTO dto) {
        roleService.create(dto);
        return Result.success();
    }

    /**
     * 该方法编辑指定角色。
     *
     * @param id id
     * @param dto 参数
     * @return Result
     */
    @PostMapping("/edit_role/{id}")
    @PreAuthorize("hasAuthority('role:edit')")
    public Result edit(@PathVariable("id") int id, @RequestBody CreateRoleDTO dto) {
        roleService.edit(id, dto);
        return Result.success();
    }

    /**
     * 该方法删除指定角色。
     *
     * @param id id
     * @return Result
     */
    @PostMapping("/del_role/{id}")
    @PreAuthorize("hasAuthority('role:del')")
    public Result del(@PathVariable("id") int id) {
        roleService.del(id);
        return Result.success();
    }

    /**
     * 该方法为指定角色批量添加权限。
     *
     * @param id id
     * @param dto 参数
     * @return Result
     */
    @PostMapping("/add_permissions_to_role/{roleId}")
    @PreAuthorize("hasAuthority('role:addPermission')")
    public Result addPermissions(@PathVariable("roleId") int id, @RequestBody AddPermissionDTO dto) {
        roleService.addPermissions(id, dto);
        return Result.success();
    }
}
