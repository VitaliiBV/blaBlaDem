package com.example.blabladem.business.service.impl;

import com.example.blabladem.dto.CustomUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Value("${spring.security.oauth2.user.username}")
    private String username;

    @Value("${spring.security.oauth2.user.password}")
    private String password;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (s.equals(username) && s.equals(password)) {
            return new CustomUserDetails(username, password);
        }
        throw new UsernameNotFoundException("Can't find user with username: " + s);
    }

}
