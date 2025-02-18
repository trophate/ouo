package com.trophate.ouo.auth.controller;

import com.trophate.ouo.auth.dto.CreatePermissionDTO;
import com.trophate.ouo.auth.service.PermissionService;
import com.trophate.ouo.framework.result.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    /**
     * 该方法创建一个权限。
     *
     * @param dto 参数
     * @return Result
     */
    @PostMapping("/create_permission")
    @PreAuthorize("hasAuthority('permission:create')")
    public Result create(@RequestBody CreatePermissionDTO dto) {
        permissionService.create(dto);
        return Result.success();
    }

    /**
     * 该方法编辑指定权限。
     *
     * @param id id
     * @param dto 参数
     * @return Result
     */
    @PostMapping("/edit_permission/{id}")
    @PreAuthorize("hasAuthority('permission:edit')")
    public Result edit(@PathVariable("id") int id, @RequestBody CreatePermissionDTO dto) {
        permissionService.edit(id, dto);
        return Result.success();
    }

    /**
     * 该方法删除指定权限。
     *
     * @param id id
     * @return Result
     */
    @PostMapping("/del_permission/{id}")
    @PreAuthorize("hasAuthority('permission:del')")
    public Result del(@PathVariable("id") int id) {
        permissionService.del(id);
        return Result.success();
    }
}
