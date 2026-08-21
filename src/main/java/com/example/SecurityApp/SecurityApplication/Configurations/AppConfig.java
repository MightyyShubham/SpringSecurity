package com.example.SecurityApp.SecurityApplication.Configurations;

import com.example.SecurityApp.SecurityApplication.Entities.User;
import com.example.SecurityApp.SecurityApplication.Repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByEmail("test@gmail.com").isEmpty()) {


                User user = new User();
                user.setName("Test User");
                user.setEmail("test@gmail.com");


                user.setPassword(
                        passwordEncoder.encode("test123")
                );
                userRepository.save(user);
                System.out.println("Test user created");
            }
        };
    }
}
