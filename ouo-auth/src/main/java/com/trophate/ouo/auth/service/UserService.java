package com.trophate.ouo.auth.service;

import com.trophate.ouo.auth.constant.AuthConstant;
import com.trophate.ouo.auth.dto.AddRoleDTO;
import com.trophate.ouo.auth.dto.InfoOfUserEditDTO;
import com.trophate.ouo.auth.dto.UserRegisterDTO;
import com.trophate.ouo.auth.entity.User;
import com.trophate.ouo.auth.entity.UserRole;
import com.trophate.ouo.auth.enumx.SexEnum;
import com.trophate.ouo.auth.exception.UsernameExistException;
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
     * 注册
     */
    public void register(UserRegisterDTO dto) {
        if (userRepository.findUserByUsername(dto.getUsername()) != null) {
            throw new UsernameExistException();
        }
        var user = new User();
        user.setUsername(dto.getUsername());
        var encoder = new BCryptPasswordEncoder(AuthConstant.BCRYPT_LEN);
        String encryptedPassword = "{bcrypt}" + encoder.encode(dto.getPassword());
        user.setPassword(encryptedPassword);
        user.setSex(SexEnum.MAN.getCode());
        userRepository.save(user);
    }

    /**
     * 注销
     */
    public void cancel(int id) {
        userRepository.deleteByIdInLogical(id);
    }

    /**
     * 编辑基础信息
     */
    public void editBaseInfo(int id, InfoOfUserEditDTO dto) {
        User user = userRepository.findById(id);
        user.setSex(dto.getSex());
        user.setProfile(dto.getProfile());
        userRepository.save(user);
    }

    /**
     * 添加角色
     */
    public void addRole(int id, AddRoleDTO dto) {
        userRoleService.deleteByUserId(id);
        var userRoles = new ArrayList<UserRole>();
        for (int roleId : dto.getRoleIds()) {
            var userRole = new UserRole();
            userRole.setUserId(id);
            userRole.setRoleId(roleId);
            userRoles.add(userRole);
        }
        userRoleService.saveAll(userRoles);
    }
}
