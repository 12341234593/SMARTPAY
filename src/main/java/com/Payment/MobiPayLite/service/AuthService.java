package com.Payment.MobiPayLite.service;

import com.Payment.MobiPayLite.dto.LoginDTO;
import com.Payment.MobiPayLite.dto.UserSignupDTO;
import com.Payment.MobiPayLite.entity.UserEntity;
import com.Payment.MobiPayLite.entity.Wallet;
import com.Payment.MobiPayLite.repository.UserRepository;
import com.Payment.MobiPayLite.repository.WalletRepository;
import com.Payment.MobiPayLite.role.Role;
import com.Payment.MobiPayLite.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    public AuthService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    //    Registering the User
    public String resgisterUser(UserSignupDTO dto)
    {
        UserEntity entity=new UserEntity();
        entity.setUser_name(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setRole(Role.valueOf(dto.getRole()));

        UserEntity savedUser= userRepository.save(entity);
//        Create wallet for this user

        Wallet wallet=new Wallet();
        wallet.setUser(savedUser);
        wallet.setBalance(100.0);
        walletRepository.save(wallet);



        return " User registered successfully!";
    }

//

public String Login(LoginDTO dto) {
    UserDetails userDetails;
    System.out.println("Email: " + dto.getEmail());
    System.out.println("Raw Password: " + dto.getPassword());

    UserEntity user = userRepository.findByEmail(dto.getEmail())
            .orElseThrow(() -> new RuntimeException("Invalid email or password"));

    System.out.println("Encoded Password in DB: " + user.getPassword());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );
        userDetails = userDetailsService.loadUserByUsername(dto.getEmail());


    return jwtTokenUtil.generateToken(userDetails);
}

    public void registerUser(UserSignupDTO userDTO) {

            UserEntity user = new UserEntity();
            user.setUser_name(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // TODO: encrypt password
            user.setRole(Role.valueOf(userDTO.getRole()));

        UserEntity savedUser= userRepository.save(user);
//        Create wallet for this user

        Wallet wallet=new Wallet();
        wallet.setUser(savedUser);
        wallet.setBalance(100.0);
        walletRepository.save(wallet);

    }
}
