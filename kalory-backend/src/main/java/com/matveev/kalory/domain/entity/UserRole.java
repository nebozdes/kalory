package com.matveev.kalory.domain.entity;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    CLIENT,
    MODERATOR,
    ADMINISTRATOR;

    @Override
    public String getAuthority() {
        return toString();
    }
}
