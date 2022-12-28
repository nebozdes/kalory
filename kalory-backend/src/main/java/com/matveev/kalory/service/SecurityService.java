package com.matveev.kalory.service;

import com.matveev.kalory.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SecurityService extends UserDetailsService {

    User getCurrentUser();
}
