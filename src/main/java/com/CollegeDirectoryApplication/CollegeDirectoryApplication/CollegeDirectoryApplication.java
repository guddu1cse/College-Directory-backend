package com.CollegeDirectoryApplication.CollegeDirectoryApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CollegeDirectoryApplication implements CommandLineRunner {

	@Autowired private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(CollegeDirectoryApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println("server is now live at port 6060 !");
		System.out.println(passwordEncoder.encode("admin"));
	}
}
