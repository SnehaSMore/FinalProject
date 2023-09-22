package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.TakeOrder;

public interface TakeOrderRepository extends JpaRepository<TakeOrder, Long> 
{
	
	public List<TakeOrder> findByOrderId(long orderId);
	List<TakeOrder> findByCustomerCustomerId(long customerId);
	
}
