package com.github.hanjungho.oauth2jwttest.repository;

import com.github.hanjungho.oauth2jwttest.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
}
