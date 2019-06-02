package com.opteamix.training.dal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.opteamix.training.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	
}
