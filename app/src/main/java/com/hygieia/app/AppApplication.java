package com.hygieia.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hygieia.AppLogger.AppLogger;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
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
