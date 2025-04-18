package com.example.demouniclubBE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DemouniclubBeApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DemouniclubBeApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemouniclubBeApplication.class);
	}
}
