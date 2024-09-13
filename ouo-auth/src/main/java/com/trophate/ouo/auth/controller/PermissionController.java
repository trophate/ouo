package com.trophate.ouo.auth.controller;


import com.trophate.ouo.auth.dto.PermissionCreateDTO;
import com.trophate.ouo.auth.service.PermissionService;
import com.trophate.ouo.framework.result.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/web/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    /**
     * 创建
     */
    @PostMapping
    @PreAuthorize("hasAuthority('permission:create')")
    public Result create(@RequestBody PermissionCreateDTO dto) {
        permissionService.create(dto);
        return Result.success();
    }

    /**
     * 编辑
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('permission:edit')")
    public Result edit(@PathVariable("id") int id, @RequestBody PermissionCreateDTO dto) {
        permissionService.edit(id, dto);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('permission:del')")
    public Result del(@PathVariable("id") int id) {
        permissionService.del(id);
        return Result.success();
    }
}
