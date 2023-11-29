package com.ellencodes.springsecurity.config;


import com.ellencodes.springsecurity.entity.Role;
import com.ellencodes.springsecurity.entity.User;
import com.ellencodes.springsecurity.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminDataInitializer {

    @Bean
    public ApplicationRunner initializeAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!userRepository.existsByEmail("admin@example.com")) {
                User admin = User.builder()
                        .email("admin@example.com")
                        .password(passwordEncoder.encode("adminPassword"))
                        .role(Role.ADMIN)
                        .build();

                userRepository.save(admin);
            }
        };
    }
}

