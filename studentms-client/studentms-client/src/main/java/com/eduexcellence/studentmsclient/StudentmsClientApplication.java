package com.eduexcellence.studentmsclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class StudentmsClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentmsClientApplication.class, args);
	}

}
