package com.tecsup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TareaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TareaServiceApplication.class, args);
	}
}