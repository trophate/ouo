package com.trophate.ouo.auth.repository;

import com.trophate.ouo.auth.entity.User;
import com.trophate.ouo.framework.jpa.CustomRepository;

public interface UserRepository extends CustomRepository<User, Integer> {

    User findUserByUsername(String username);

    User findById(int id);
}
