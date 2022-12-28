package com.matveev.kalory;

import com.matveev.kalory.domain.entity.UserEntity;
import com.matveev.kalory.model.User;

import java.time.LocalDate;
import java.util.List;

import static com.matveev.kalory.RandomUtils.aRandomId;
import static com.matveev.kalory.RandomUtils.aRandomString;
import static com.matveev.kalory.domain.entity.UserRole.ADMINISTRATOR;
import static com.matveev.kalory.domain.entity.UserRole.CLIENT;
import static com.matveev.kalory.domain.entity.UserRole.MODERATOR;
import static com.matveev.kalory.domain.entity.UserStatus.ACTIVE;
import static com.matveev.kalory.model.id.UserId.userId;

public interface UserTestData {

    default User.UserBuilder anUser(String login) {
        return User.builder()
                .id(userId(aRandomId()))
                .registrationDate(LocalDate.now())
                .status(ACTIVE)
                .login(login)
                .roles(List.of(CLIENT, MODERATOR, ADMINISTRATOR));
    }

    default UserEntity.UserEntityBuilder anUserEntity(String login) {

        return UserEntity.builder()
                .id(aRandomId())
                .registrationDate(LocalDate.now())
                .status(ACTIVE)
                .login(login)
                .roles("CLIENT:MODERATOR:ADMINISTRATOR")
                .password(aRandomString(8));
    }
}
