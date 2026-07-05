package com.tecsup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ProyectoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoServiceApplication.class, args);
	}

}