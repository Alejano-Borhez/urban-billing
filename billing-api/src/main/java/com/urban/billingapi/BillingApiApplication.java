package com.urban.billingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;

import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
//@Import(SpringDataRestConfiguration.class)
public class BillingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingApiApplication.class, args);
	}

	@Bean
	public BraintreeGateway paymentGateway() {
		return new BraintreeGateway(
				Environment.SANDBOX,
				"x32vvjn983w6hrbp",
				"hwp4z8mxypt8qzmf",
				"42f9e99b1baa410865f276d49575379a"
		);
	}
}

