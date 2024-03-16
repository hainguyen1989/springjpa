package com.haint.springjpa;

import com.haint.springjpa.db.UserRepository;
import com.haint.springjpa.entity.Role;
import com.haint.springjpa.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringjpaApplication {

    @Autowired
    private UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) {
        SpringApplication.run(SpringjpaApplication.class, args);
    }

    // Run this if app.db.init.enabled = true
    @Bean
    @ConditionalOnProperty(prefix = "app", name = "db.init.enabled", havingValue = "true")
    public CommandLineRunner demoCommandLineRunner() {
        return args -> {

            logger.info("Start creating sample data.....");

            User u1 = new User("eric.nguyen", "Hanoi", "Nguyen The Hai");
            User u2 = new User("shirley.trang", "Hanoi", "Hoang Phuong Trang");

            Role r1 = new Role("Role 1");
            Role r2 = new Role("Role 2");

            u1.setRoles(Set.of(r1, r2));
            u2.setRoles(Set.of(r2));

            userRepository.saveAll(List.of(u1, u2));

            logger.info("Sample data are created!");
        };
    }
}
