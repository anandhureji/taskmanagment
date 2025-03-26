package com.tm.submissionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SubmissionserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubmissionserviceApplication.class, args);
	}

}
