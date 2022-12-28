package com.matveev.kalory.model;

import com.matveev.kalory.domain.entity.UserRole;
import com.matveev.kalory.domain.entity.UserStatus;
import com.matveev.kalory.model.id.UserId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@ToString
@Validated
@EqualsAndHashCode
public class User {

    @NotNull
    private final UserId id;

    @NotNull
    @NotEmpty
    private final String login;

    @NotNull
    private final UserStatus status;

    @NotNull
    @NotEmpty
    private final List<UserRole> roles;

    @NotNull
    private final LocalDate registrationDate;
}
