package com.trophate.ouo.auth.controller;

import com.trophate.ouo.auth.dto.AddRoleDTO;
import com.trophate.ouo.auth.dto.InfoOfUserEditDTO;
import com.trophate.ouo.auth.dto.UserRegisterDTO;
import com.trophate.ouo.auth.service.UserService;
import com.trophate.ouo.framework.result.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/web/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    @PreAuthorize("hasAuthority('user:register')")
    public Result register(@RequestBody UserRegisterDTO dto) {
        userService.register(dto);
        return Result.success();
    }

    /**
     * 注销
     */
    @DeleteMapping("/{id}/cancel")
    @PreAuthorize("hasAuthority('user:cancel')")
    public Result cancel(@PathVariable("id") int id) {
        userService.cancel(id);
        return Result.success();
    }

    /**
     * 编辑信息
     */
    @PutMapping("/{id}/base-info")
    @PreAuthorize("hasAuthority('user:info:edit')")
    public Result editBaseInfo(@PathVariable("id") int id, @RequestBody InfoOfUserEditDTO dto) {
        userService.editBaseInfo(id, dto);
        return Result.success();
    }

    /**
     * 添加角色
     */
    @PutMapping("/{id}/add-role")
    @PreAuthorize("hasAuthority('user:addRole')")
    public Result addRole(@PathVariable("id") int id, @RequestBody AddRoleDTO dto) {
        userService.addRole(id, dto);
        return Result.success();
    }
}
