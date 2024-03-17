package com.cityfine.checkdot_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@EntityScan
@SpringBootApplication
@ComponentScan("com.cityfine.checkdot_server")
public class CheckDotServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckDotServerApplication.class, args);
	}

}
