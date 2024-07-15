package com.trophate.ouo.auth.repository;

import com.trophate.ouo.auth.entity.Permission;
import com.trophate.ouo.framework.jpa.CustomRepository;

import java.util.List;

public interface PermissionRepository extends CustomRepository<Permission, Integer> {

    List<Permission> findByIdIn(List<Integer> ids);

    List<Permission> findAll();
}
