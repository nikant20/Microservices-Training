package com.opteamix.training.service;

import java.util.List;

import com.opteamix.training.domain.Product;

public interface ProductService {

	int addNewProduct(Product p);

	Product findById(int id);

	List<Product> findAll();

}