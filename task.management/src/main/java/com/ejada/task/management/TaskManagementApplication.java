package com.ejada.task.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TaskManagementApplication {

	public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println("Password is " + encoder.encode("123"));
		SpringApplication.run(TaskManagementApplication.class, args);
	}

}
