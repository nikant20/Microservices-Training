package com.opteamix.training.dal;

import java.util.List;

import com.opteamix.training.domain.Product;

public interface ProductDAO {
	public Product save(Product toBeSaved);
	public Product findById(int id);
	public List<Product> findAll();
	//update and delete methods can be added
}
