package com.Payment.MobiPayLite.controller;

import com.Payment.MobiPayLite.dto.UpdateProfileDto;
import com.Payment.MobiPayLite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private  UserService userService;
@PutMapping("/profile/update")
    public ResponseEntity<String> updateProfile(@RequestBody UpdateProfileDto dto)
{
    return ResponseEntity.ok(userService.UpdateProfile(dto));
}
}
