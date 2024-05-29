package com.sivalabs.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) {
        if(username.equalsIgnoreCase("user")) {
            return new SecurityUser("user", "password", "ROLE_USER");
        }
        throw new UsernameNotFoundException("No user found with username " + username);
    }
}
