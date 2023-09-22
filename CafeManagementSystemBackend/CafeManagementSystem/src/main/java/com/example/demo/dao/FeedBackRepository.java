package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.FeedBack;

public interface FeedBackRepository extends JpaRepository<FeedBack, Integer>  
{
	List<FeedBack> findBycustomerNameIgnoreCase(String customerName);
	
	@Query(value = "SELECT * FROM FeedBack f WHERE f.customerName = ?1",nativeQuery = true )
	
	public Optional<FeedBack> findcustomerByName (String title);
}
