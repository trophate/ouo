package com.trophate.ouo.auth.repository;

import com.trophate.ouo.auth.entity.UserRole;
import com.trophate.ouo.framework.jpa.CustomRepository;

import java.util.List;

public interface UserRoleRepository extends CustomRepository<UserRole, Integer> {

    void deleteByUserId(int userId);

    List<UserRole> findByUserId(int userId);
}
