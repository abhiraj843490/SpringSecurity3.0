package com.spring.security.SpringSecurity.config;

import com.spring.security.SpringSecurity.entity.UserEntity;
import com.spring.security.SpringSecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional <UserEntity>userEntity = userRepository.findByName(username);

        return userEntity.map(UserInfoDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("user not found "+username));

    }
}
