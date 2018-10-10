package com.capgemini.restfulProject.repository;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.restfulProject.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>{

}
