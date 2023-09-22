package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Product;

public interface ProductService {

	public List<Product>findAll();
	public void save(Product p);
	public Optional<Product>findById(int id);
	public void updateproduct(Product p);
	public void deleteById(int id);
	public List<Product>findByProducdname(String productName);
	public Optional<Product>getProductByname(String productName);
		
}
