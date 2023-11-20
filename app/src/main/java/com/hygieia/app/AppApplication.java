package com.hygieia.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import com.hygieia.AppLogger.AppLogger;
import com.hygieia.app.Repositories.EmployeeRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication( exclude = { SecurityAutoConfiguration.class })

public class AppApplication {

	public AppLogger logger;

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@PostConstruct
	public void initLogger() {
		//initialize logger here
		logger = AppLogger.getInstance();
		logger.logInfo("Logger initialized");

	}

	

}
