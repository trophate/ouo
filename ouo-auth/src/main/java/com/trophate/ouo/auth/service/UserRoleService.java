package com.trophate.ouo.auth.service;

import com.trophate.ouo.auth.entity.UserRole;
import com.trophate.ouo.auth.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    /**
     * 批量保存
     */
    public void saveAll(List<UserRole> userRoles) {
        userRoleRepository.saveAll(userRoles);
    }

    /**
     * 通过用户id删除
     */
    public void deleteByUserId(int userId) {
        userRoleRepository.deleteByUserId(userId);
    }

    /**
     * 通过角色id获取角色id集合
     */
    public List<Integer> getRoleIdsByUserId(int userId) {
        List<UserRole> userRoles = userRoleRepository.findByUserId(userId);
        var roleIds = new ArrayList<Integer>();
        for (UserRole userRole : userRoles) {
            roleIds.add(userRole.getRoleId());
        }
        return roleIds;
    }
}
