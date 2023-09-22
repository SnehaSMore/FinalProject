package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Category;


public interface CategoryService {

	public List<Category>findAll();
	public void save(Category c);
	public Optional<Category>findById(int id);
	public void updatecategory(Category c);
	public void deleteById(int id);
	public List<Category>findByCategoryname(String categoryName);
	public Optional<Category> getCategoryByname(String categoryName);
}


