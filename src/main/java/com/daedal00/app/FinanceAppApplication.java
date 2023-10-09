package com.daedal00.app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class FinanceAppApplication {
	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	public static void main(String[] args) {
		SpringApplication.run(FinanceAppApplication.class, args);
	}
}