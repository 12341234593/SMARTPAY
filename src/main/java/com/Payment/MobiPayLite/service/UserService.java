package com.Payment.MobiPayLite.service;

import com.Payment.MobiPayLite.dto.UpdateProfileDto;
import com.Payment.MobiPayLite.entity.UserEntity;
import com.Payment.MobiPayLite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String UpdateProfile(UpdateProfileDto dto)
    {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String email=auth.getName();

        UserEntity user= userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not Found"));
        user.setUser_name(dto.getName());
        user.setEmail(dto.getEmail());
        userRepository.save(user);
        return "Profile Updated Successfully";
    }
}
