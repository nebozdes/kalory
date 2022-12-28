package com.matveev.kalory.service.impl;

import com.matveev.kalory.domain.entity.UserEntity;
import com.matveev.kalory.domain.repository.UserRepository;
import com.matveev.kalory.model.User;
import com.matveev.kalory.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.matveev.kalory.mapper.UserMapper.map;

@Service
@RequiredArgsConstructor
public class SecurityServiceBean implements SecurityService {

    private final UserRepository userRepository;

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return map(loadUserByUsername(authentication.getName()));
    }

    @Override
    public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username).orElseThrow(
                () -> new UsernameNotFoundException(
                        String.format("User with login %s not found!", username)
                )
        );
    }


}
