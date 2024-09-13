package com.trophate.ouo.auth.controller;

import com.trophate.ouo.auth.dto.AddPermissionDTO;
import com.trophate.ouo.auth.dto.RoleCreateDTO;
import com.trophate.ouo.auth.service.RoleService;
import com.trophate.ouo.framework.result.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/web/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 创建
     */
    @PostMapping
    @PreAuthorize("hasAuthority('role:create')")
    public Result create(@RequestBody RoleCreateDTO dto) {
        roleService.create(dto);
        return Result.success();
    }

    /**
     * 添加权限
     */
    @PutMapping("/{id}/add-permission")
    @PreAuthorize("hasAuthority('role:addPermission')")
    public Result addRole(@PathVariable("id") int id, @RequestBody AddPermissionDTO dto) {
        roleService.addPermission(id, dto);
        return Result.success();
    }
}
