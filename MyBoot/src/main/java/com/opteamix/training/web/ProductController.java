package com.opteamix.training.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opteamix.training.domain.Product;
import com.opteamix.training.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	ProductService service;
	
	//@GetMapping("/products")
	@RequestMapping(method=RequestMethod.GET,value="/products")
	public List<Product> getAll(){
		return service.findAll();
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getById(@PathVariable("id") int id) {
		Product p = service.findById(id);
		if(p==null) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Product>(p, HttpStatus.OK);
		}
		
	}
	
	@PostMapping("/products")
	public ResponseEntity add(@RequestBody Product p) {
		try {
			int id = service.addNewProduct(p);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create("/products/"+id));
			return new ResponseEntity<>(headers, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		
	}
}
