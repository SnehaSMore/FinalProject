package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductRepository;
import com.example.demo.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository dao;
	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public void save(Product p) {
		// TODO Auto-generated method stub
		dao.save(p);
	}

	@Override
	public Optional<Product> findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public void updateproduct(Product p) {
		// TODO Auto-generated method stub
		dao.save(p);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	@Override
	public List<Product> findByProducdname(String productName) {
		// TODO Auto-generated method stub
		return this.dao.findByproductNameIgnoreCase(productName);
	}

	@Override
	public Optional<Product> getProductByname(String productName) {
		// TODO Auto-generated method stub
		return this.dao.findproductByName(productName);
	}

	
	
	
}
