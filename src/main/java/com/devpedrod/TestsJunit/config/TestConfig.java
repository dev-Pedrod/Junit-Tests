package com.devpedrod.TestsJunit.config;

import com.devpedrod.TestsJunit.domain.User;
import com.devpedrod.TestsJunit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public void startDB() {
        User user1 = new User(null, "Pedro", "Pedro@gmail.com", "123");
        User user2 = new User(null, "Maria", "Maria@gmail.com", "123");
        User user3 = new User(null, "João", "João@gmail.com", "123");

        userRepository.saveAll(List.of(user1, user2, user3));
    }
}
