package com.opteamix.training;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.opteamix.training.dal.ProductDAO;
import com.opteamix.training.dal.ProductDAOInMemoryImpl;
import com.opteamix.training.service.ProductService;
import com.opteamix.training.service.ProductServiceImpl;
import com.opteamix.training.ui.ProductUI;

@Configuration
public class MyBootConfiguration {

	/*
	 * @Bean public ProductDAO dao() { return new ProductDAOInMemoryImpl(); }
	 * 
	 * @Bean public ProductService service() { ProductServiceImpl service = new
	 * ProductServiceImpl(); service.setDao(dao()); return service; }
	 * 
	 * @Bean public ProductUI ui() { ProductUI ui = new ProductUI();
	 * ui.setService(service()); return ui; }
	 */

}
