package com.opteamix.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

import com.opteamix.training.domain.Product;
import com.opteamix.training.ui.ProductUI;

@SpringBootApplication
@EnableDiscoveryClient
public class MyBootApplication {

	public static void main(String[] args) {
		// ApplicationContext ctx = 
		SpringApplication.run(MyBootApplication.class, args);
		// ProductUI ui = ctx.getBean(ProductUI.class);
		// ui.addProductWithUi();
	}
}
