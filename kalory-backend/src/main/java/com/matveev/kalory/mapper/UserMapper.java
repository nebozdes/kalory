package com.matveev.kalory.mapper;

import com.matveev.kalory.domain.entity.UserEntity;
import com.matveev.kalory.model.User;
import lombok.experimental.UtilityClass;

import static com.matveev.kalory.model.id.UserId.userId;

@UtilityClass
public class UserMapper {

    public static User map(UserEntity entity) {
        return User.builder()
                .id(userId(entity.getId()))
                .login(entity.getLogin())
                .registrationDate(entity.getRegistrationDate())
                .roles(entity.getRoles())
                .status(entity.getStatus())
                .build();
    }
}
