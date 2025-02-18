package com.trophate.ouo.auth.service;

import com.trophate.ouo.auth.constant.AuthConstant;
import com.trophate.ouo.auth.dto.AddRoleDTO;
import com.trophate.ouo.auth.dto.EditUserInfoDTO;
import com.trophate.ouo.auth.dto.RegisterUserDTO;
import com.trophate.ouo.auth.entity.User;
import com.trophate.ouo.auth.entity.UserRole;
import com.trophate.ouo.auth.enums.SexEnum;
import com.trophate.ouo.auth.exception.UsernameAlreadyExistException;
import com.trophate.ouo.auth.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;

    public UserService(UserRepository userRepository, UserRoleService userRoleService) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
    }

    public User getByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    /**
     * 该方法注册一个新用户。
     *
     * @param dto 参数
     */
    public void register(RegisterUserDTO dto) {
        if (userRepository.findUserByUsername(dto.getUsername()) != null) {
            throw new UsernameAlreadyExistException();
        }
        var user = new User();
        user.setUsername(dto.getUsername());
        var encoder = new BCryptPasswordEncoder(AuthConstant.BCRYPT_DEFAULT_LEN);
        String encryptedPassword = "{bcrypt}" + encoder.encode(dto.getPassword());
        user.setPassword(encryptedPassword);
        user.setSex(SexEnum.MAN.getCode());
        userRepository.save(user);
    }

    /**
     * 该方法注销指定用户。
     *
     * @param id id
     */
    public void cancel(int id) {
        userRepository.deleteByIdInLogical(id);
    }

    /**
     * 该方法编辑用户信息。
     *
     * @param id id
     * @param dto 参数
     */
    public void editInfo(int id, EditUserInfoDTO dto) {
        User user = userRepository.findById(id);
        user.setSex(dto.getSex());
        user.setProfile(dto.getProfile());
        userRepository.save(user);
    }

    /**
     * 该方法为指定用户批量添加角色。
     *
     * @param id id
     * @param dto 参数
     */
    public void addRoles(int id, AddRoleDTO dto) {
        userRoleService.deleteByUserId(id);
        var userRoles = new ArrayList<UserRole>();
        for (int roleId : dto.getRoleIds()) {
            var userRole = new UserRole();
            userRole.setUserId(id);
            userRole.setRoleId(roleId);
            userRoles.add(userRole);
        }
        userRoleService.saveInBatch(userRoles);
    }
}
