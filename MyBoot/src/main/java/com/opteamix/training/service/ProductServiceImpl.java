package com.opteamix.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.opteamix.training.dal.ProductDAO;
import com.opteamix.training.dal.ProductRepository;
import com.opteamix.training.domain.Product;

@Component
@Transactional
public class ProductServiceImpl implements ProductService {
	
	
	ProductDAO dao;
	
	@Autowired
	public void setDao(ProductDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public int addNewProduct(Product p) {
		if(p.getPrice()*p.getQoh() >= 10000) {
			Product added = dao.save(p);
			return added.getId();
		}else {
			throw new IllegalArgumentException("value of product below 10k");
		}
	}

	@Override
	public Product findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<Product> findAll() {
		return dao.findAll();
	}
	
	
}
