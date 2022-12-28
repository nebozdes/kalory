package com.matveev.kalory.domain.repository;

import com.matveev.kalory.domain.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends CustomRepository<UserEntity> {

    Optional<UserEntity> findByLogin(String login);
}
