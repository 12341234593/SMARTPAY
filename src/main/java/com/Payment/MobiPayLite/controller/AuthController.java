package com.Payment.MobiPayLite.controller;

import com.Payment.MobiPayLite.dto.LoginDTO;
import com.Payment.MobiPayLite.dto.ResetPasswordRequest;
import com.Payment.MobiPayLite.dto.UserSignupDTO;
import com.Payment.MobiPayLite.service.AuthService;
import com.Payment.MobiPayLite.service.PasswordResetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/signup")
    public ResponseEntity<String> openSignupFromBrowser() {
        return ResponseEntity.ok("Signup works (but use POST for real data)");
    }


    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody @Valid UserSignupDTO dto)
    {
        String response=authService.resgisterUser(dto);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO dto)
    {
        try{
        String token = authService.Login(dto);
        return ResponseEntity.ok("Bearer " + token);
    } catch (RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
    }

//    For generating the token and resetting the password
    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email)
    {
        String token= passwordResetService.createResetToken(email);
        return ResponseEntity.ok("Reset token: "+ token);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest dto)
    {
        passwordResetService.resetPassword(dto.getToken(), dto.getNewPassword());
        return ResponseEntity.ok("Password updated succesfully");
    }


//    @GetMapping("/user/profile")
//    public ResponseEntity<?> getProfile() {
//        return ResponseEntity.ok("Your Profile");
//    }

}
