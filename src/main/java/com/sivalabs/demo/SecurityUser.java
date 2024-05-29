package com.sivalabs.demo;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class SecurityUser extends User {

    public SecurityUser(String email, String password, String role) {
        super(email, password, List.of(new SimpleGrantedAuthority(role)));
    }
}
