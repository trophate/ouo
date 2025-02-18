package com.trophate.ouo.auth.controller;

import com.trophate.ouo.auth.dto.AddRoleDTO;
import com.trophate.ouo.auth.dto.EditUserInfoDTO;
import com.trophate.ouo.auth.dto.RegisterUserDTO;
import com.trophate.ouo.auth.service.UserService;
import com.trophate.ouo.framework.result.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 该方法注册一个新用户。
     *
     * @param dto 参数
     * @return Result
     */
    @PostMapping("/register_user")
    @PreAuthorize("hasAuthority('user:register')")
    public Result register(@RequestBody RegisterUserDTO dto) {
        userService.register(dto);
        return Result.success();
    }

    /**
     * 该方法注销指定用户。
     *
     * @param id id
     * @return Result
     */
    @PostMapping("/cancel_user/{id}")
    @PreAuthorize("hasAuthority('user:cancel')")
    public Result cancel(@PathVariable("id") int id) {
        userService.cancel(id);
        return Result.success();
    }

    /**
     * 该方法编辑用户信息。
     *
     * @param id id
     * @param dto 参数
     * @return Result
     */
    @PostMapping("/edit_user_info/{id}/")
    @PreAuthorize("hasAuthority('user:info:edit')")
    public Result editInfo(@PathVariable("id") int id, @RequestBody EditUserInfoDTO dto) {
        userService.editInfo(id, dto);
        return Result.success();
    }

    /**
     * 该方法为指定用户批量添加角色。
     *
     * @param id id
     * @param dto 参数
     * @return Result
     */
    @PostMapping("/add_roles_to_user/{userId}")
    @PreAuthorize("hasAuthority('user:addRole')")
    public Result addRoles(@PathVariable("userId") int id, @RequestBody AddRoleDTO dto) {
        userService.addRoles(id, dto);
        return Result.success();
    }
}
