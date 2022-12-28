package com.matveev.kalory.service;

import com.matveev.kalory.UserTestData;
import com.matveev.kalory.domain.entity.UserEntity;
import com.matveev.kalory.domain.repository.UserRepository;
import com.matveev.kalory.service.impl.SecurityServiceBean;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SecurityServiceTest implements UserTestData {

    private final UserRepository repository = mock(UserRepository.class);
    private final SecurityService service = new SecurityServiceBean(repository);

    @Test
    public void should_throw_exception_if_user_with_login_not_found() {
        // given
        final var login = "some_login";
        when(repository.findByLogin(login)).thenReturn(empty());

        // when/then
        assertThatThrownBy(() -> service.loadUserByUsername(login))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessage("User with login some_login not found!");
    }

    @Test
    public void should_return_mapped_dto_if_user_is_found() {
        // given
        final var login = "login";
        final var entity = anUserEntity(login).id(123L).build();
        when(repository.findByLogin(login)).thenReturn(of(entity));

        // when
        final var result = (UserEntity) service.loadUserByUsername(login);

        // then
        assertThat(entity).isEqualTo(result);
    }
}
