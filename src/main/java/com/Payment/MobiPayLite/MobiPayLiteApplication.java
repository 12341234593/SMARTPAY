package com.Payment.MobiPayLite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MobiPayLiteApplication {

	public static void main(String[] args) {

		SpringApplication.run(MobiPayLiteApplication.class, args);

	}
	

}
