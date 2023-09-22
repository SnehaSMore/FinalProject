package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>
{
	List<Product> findByproductNameIgnoreCase(String productName);
		
	@Query(value = "SELECT * FROM PRODUCT pd WHERE pd.productName = ?1",nativeQuery = true )
	
	public Optional<Product> findproductByName (String title);
}
