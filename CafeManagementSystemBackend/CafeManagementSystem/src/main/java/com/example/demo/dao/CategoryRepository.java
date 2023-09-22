package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Category;

@Repository

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	
	List<Category> findBycategoryNameIgnoreCase(String categoryName);
	
	@Query(value = "SELECT * FROM Category p WHERE p.categoryName = ?1",nativeQuery = true )
	
	public Optional<Category> findcategoryByName(String title);

	
}



