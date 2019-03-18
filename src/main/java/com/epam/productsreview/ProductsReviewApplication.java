package com.epam.productsreview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductsReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsReviewApplication.class, args);
	}

}
