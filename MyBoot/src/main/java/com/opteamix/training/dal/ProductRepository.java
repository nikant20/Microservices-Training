package com.opteamix.training.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.opteamix.training.domain.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>  {
	public Product findById(int id);
	public List<Product> findAll();
	
	@Query("select p from Product p where p.price < 100")
	public List<Product> complexQuery();
}
