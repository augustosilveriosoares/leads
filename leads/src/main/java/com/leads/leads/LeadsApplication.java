package com.leads.leads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication

public class LeadsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeadsApplication.class, args);
	}

}
