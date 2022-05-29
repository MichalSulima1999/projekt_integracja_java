package com.example.projekt;

import com.example.projekt.domain.Role;
import com.example.projekt.domain.User;
import com.example.projekt.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class ProjektApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjektApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	CommandLineRunner run(UserService userService) {
//		return args -> {
//			userService.saveRole(new Role(null, "ROLE_USER"));
//			userService.saveRole(new Role(null, "ROLE_MANAGER"));
//			userService.saveRole(new Role(null, "ROLE_ADMIN"));
//
//			userService.saveUser(new User("John123", "1234"));
//			userService.saveUser(new User("Tadzik", "1234"));
//			userService.saveUser(new User("Pjoter", "1234"));
//
//			userService.addRoleToUser("John123", "ROLE_USER");
//			userService.addRoleToUser("Tadzik", "ROLE_USER");
//			userService.addRoleToUser("Tadzik", "ROLE_MANAGER");
//			userService.addRoleToUser("Pjoter", "ROLE_ADMIN");
//			userService.addRoleToUser("Pjoter", "ROLE_MANAGER");
//			userService.addRoleToUser("Pjoter", "ROLE_USER");
//		};
//	}
}
