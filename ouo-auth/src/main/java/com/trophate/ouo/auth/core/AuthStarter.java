package com.trophate.ouo.auth.core;

import com.trophate.ouo.auth.constant.AuthConstant;
import com.trophate.ouo.auth.dto.CreatePermissionDTO;
import com.trophate.ouo.auth.entity.Permission;
import com.trophate.ouo.auth.entity.User;
import com.trophate.ouo.auth.enums.PermissionTypeEnum;
import com.trophate.ouo.auth.enums.SexEnum;
import com.trophate.ouo.auth.service.PermissionService;
import com.trophate.ouo.auth.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

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
     * 该方法创建一个默认管理员并赋予其初始权限。
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
            var createPermissionDTOs = new ArrayList<CreatePermissionDTO>();
            var createPermissionDTO = new CreatePermissionDTO();
            createPermissionDTO.setParentId(0);
            createPermissionDTO.setType(PermissionTypeEnum.ORDER.getCode());
            createPermissionDTO.setName("权限");
            createPermissionDTO.setCode("permission");
            permissionService.create(createPermissionDTO);
            int permissionParentId = permissionService.getByCode("permission").getParentId();
            createPermissionDTO = new CreatePermissionDTO();
            createPermissionDTO.setType(PermissionTypeEnum.BUTTON.getCode());
            createPermissionDTO.setParentId(permissionParentId);
            createPermissionDTO.setName("创建");
            createPermissionDTO.setCode("permission:create");
            createPermissionDTOs.add(createPermissionDTO);
            createPermissionDTO = new CreatePermissionDTO();
            createPermissionDTO.setType(PermissionTypeEnum.BUTTON.getCode());
            createPermissionDTO.setParentId(permissionParentId);
            createPermissionDTO.setName("编辑");
            createPermissionDTO.setCode("permission:edit");
            createPermissionDTOs.add(createPermissionDTO);
            createPermissionDTO = new CreatePermissionDTO();
            createPermissionDTO.setType(PermissionTypeEnum.BUTTON.getCode());
            createPermissionDTO.setParentId(permissionParentId);
            createPermissionDTO.setName("删除");
            createPermissionDTO.setCode("permission:del");
            createPermissionDTOs.add(createPermissionDTO);
            permissionService.createInBatch(createPermissionDTOs);
        }
    }
}
