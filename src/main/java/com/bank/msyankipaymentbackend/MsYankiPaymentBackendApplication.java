package com.bank.msyankipaymentbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsYankiPaymentBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsYankiPaymentBackendApplication.class, args);
	}

}
