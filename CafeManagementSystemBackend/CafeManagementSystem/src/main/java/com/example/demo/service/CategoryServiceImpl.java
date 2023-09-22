package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.entity.Category;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepository dao;
	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public void save(Category c) {
		// TODO Auto-generated method stub
		dao.save(c);
		
	}

	@Override
	public Optional<Category> findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public void updatecategory(Category c) {
		// TODO Auto-generated method stub
		dao.save(c);
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	@Override
	public List<Category> findByCategoryname(String categoryName) {
		// TODO Auto-generated method stub
		return this.dao.findBycategoryNameIgnoreCase(categoryName);
	}

	@Override
	public Optional<Category> getCategoryByname(String categoryName) {
		// TODO Auto-generated method stub
		return this.dao.findcategoryByName(categoryName);
	}

}