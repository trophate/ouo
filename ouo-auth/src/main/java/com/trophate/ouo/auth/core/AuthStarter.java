package com.trophate.ouo.auth.core;

import com.trophate.ouo.auth.constant.AuthConstant;
import com.trophate.ouo.auth.entity.Permission;
import com.trophate.ouo.auth.entity.User;
import com.trophate.ouo.auth.enums.PermissionTypeEnum;
import com.trophate.ouo.auth.enums.SexEnum;
import com.trophate.ouo.auth.service.PermissionService;
import com.trophate.ouo.auth.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
public class AuthStarter {

    private final UserService userService;
    private final PermissionService permissionService;

    public AuthStarter(UserService userService, PermissionService permissionService) {
        this.userService = userService;
        this.permissionService = permissionService;
    }

    @PostConstruct
    public void init() {
        createDefaultData();
    }

    /**
     * 创建默认管理员。
     */
    private void createDefaultData() {
        User admin = userService.getByUsername("admin");
        if (admin == null) {
            var user = new User();
            user.setId(1);
            user.setUsername("admin");
            var encoder = new BCryptPasswordEncoder(AuthConstant.BCRYPT_DEFAULT_LEN);
            String encryptedPassword = "{bcrypt}" + encoder.encode("1234567");
            user.setPassword(encryptedPassword);
            user.setSex(SexEnum.MAN.getCode());
            userService.save(user);
            var permissions = new ArrayList<Permission>();
            var permission = new Permission();
            permission.setParentId(0);
            permission.setType(PermissionTypeEnum.ORDER.getCode());
            permission.setName("权限");
            permission.setCode("permission");
            permissionService.save(permission);
            int permissionParentId = permission.getId();
            permission = new Permission();
            permission.setType(PermissionTypeEnum.BUTTON.getCode());
            permission.setParentId(permissionParentId);
            permission.setName("创建");
            permission.setCode("permission:create");
            permissions.add(permission);
            permission = new Permission();
            permission.setType(PermissionTypeEnum.BUTTON.getCode());
            permission.setParentId(permissionParentId);
            permission.setName("编辑");
            permission.setCode("permission:edit");
            permissions.add(permission);
            permission = new Permission();
            permission.setType(PermissionTypeEnum.BUTTON.getCode());
            permission.setParentId(permissionParentId);
            permission.setName("删除");
            permission.setCode("permission:del");
            permissions.add(permission);
            permissionService.saveInBatch(permissions);
        }
    }
}
