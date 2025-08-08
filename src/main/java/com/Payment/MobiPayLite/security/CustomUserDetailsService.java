package com.Payment.MobiPayLite.security;

import com.Payment.MobiPayLite.entity.UserEntity;
import com.Payment.MobiPayLite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    
    
@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user= repository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User Not found!!"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority(user.getRole().name()))); 

    }
}
