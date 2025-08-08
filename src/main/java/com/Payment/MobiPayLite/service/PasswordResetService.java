package com.Payment.MobiPayLite.service;

import com.Payment.MobiPayLite.entity.PasswordResetToken;
import com.Payment.MobiPayLite.entity.UserEntity;
import com.Payment.MobiPayLite.repository.PasswordResetTokenRepository;
import com.Payment.MobiPayLite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    public String createResetToken(String email)
    {
        Optional<UserEntity> useropt= userRepository.findByEmail(email);
        if(useropt.isEmpty()) throw new RuntimeException("User not found");

        UserEntity user=useropt.get();

        String token= UUID.randomUUID().toString();

        PasswordResetToken resetToken=new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUserid(user.getId());
        resetToken.setExpiry(LocalDateTime.now().plusMinutes(10));

        tokenRepository.save(resetToken);

        return  token;
    }

    public  void resetPassword(String token,String newPassword)
    {
        PasswordResetToken resetToken=tokenRepository.findByToken(token).orElseThrow(()->new RuntimeException("Invalid token"));

        if(resetToken.getExpiry().isBefore(LocalDateTime.now()))
        {
            throw  new RuntimeException("Token Expired");
        }

        UserEntity user=userRepository.findById((long) resetToken.getUserid()).orElseThrow(()->new RuntimeException("User not found"));

        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        userRepository.save(user);

        tokenRepository.delete(resetToken);
    }


}
