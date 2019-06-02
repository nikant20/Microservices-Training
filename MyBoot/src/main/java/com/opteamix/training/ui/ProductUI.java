package com.opteamix.training.ui;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.opteamix.training.domain.Product;
import com.opteamix.training.service.ProductService;

@Component
public class ProductUI {
	ProductService service;

	/*
	 * @Value("${mymessage}") String message;
	 */

	@Autowired
	public void setService(ProductService service) {
		this.service = service;
	}

	public void addProductWithUi() {
		Scanner kb = new Scanner(System.in);
		// System.out.println("Message = "+message);
		System.out.println("Enter a name: ");
		String name = kb.nextLine();
		System.out.println("Enter price: ");
		float price = Float.parseFloat(kb.nextLine());
		System.out.println("Enter qoh: ");
		int qoh = Integer.parseInt(kb.nextLine());

		Product p = new Product(name, price, qoh);

		int id = service.addNewProduct(p);
		System.out.println("Created with id: " + id);

		kb.close();
	}
}
