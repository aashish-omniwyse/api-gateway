package com.gateway.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author aashish
 */
@SpringBootApplication
public class ApiApp {
	private static final Logger logger = LoggerFactory.getLogger(ApiApp.class);

	public static void main(String[] args) {
		SpringApplication.run(ApiApp.class, args);
		logger.info("Api Gateway application started...");
	}
}
