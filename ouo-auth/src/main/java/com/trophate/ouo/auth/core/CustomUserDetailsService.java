package com.trophate.ouo.auth.core;

import com.trophate.ouo.auth.entity.User;
import com.trophate.ouo.auth.service.PermissionService;
import com.trophate.ouo.auth.service.RolePermissionService;
import com.trophate.ouo.auth.service.UserRoleService;
import com.trophate.ouo.auth.service.UserService;
import com.trophate.ouo.framework.security.CurrentUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final UserRoleService userRoleService;
    private final RolePermissionService rolePermissionService;
    private final PermissionService permissionService;

    public CustomUserDetailsService(UserService userService, UserRoleService userRoleService, RolePermissionService rolePermissionService,
                                    PermissionService permissionService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.rolePermissionService = rolePermissionService;
        this.permissionService = permissionService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        user.setLastLoginTime(LocalDateTime.now());
        userService.save(user);
        var currentUser = new CurrentUser();
        currentUser.setId(user.getId());
        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword());
        List<String> permissionCodes;
        if (!user.getUsername().equals("admin")) {
            List<Integer> roleIds = userRoleService.getRoleIdsByUserId(user.getId());
            List<Integer> permissionIds = rolePermissionService.getPermissionIdsByRoleIds(roleIds);
            permissionCodes = permissionService.getCodesByIds(permissionIds);
        } else {
            permissionCodes = permissionService.getAllCodes();
        }
        currentUser.setPermissions(permissionCodes);
        return currentUser;
    }
}
