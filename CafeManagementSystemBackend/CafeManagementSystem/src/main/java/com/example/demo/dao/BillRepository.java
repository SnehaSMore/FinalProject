package com.example.demo.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.entity.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> 
{
	
	/*@Query(value = "SELECT * FROM Bill b1 WHERE b1.customer_Name = ?1",nativeQuery = true )

	public Optional<Bill> findByCustomerCustomer_Name(String customer_Name);*/
	
	public List<Bill> findByOrderId(long orderId); //Admin
	List<Bill> findByCustomerCustomerId(long customerId);
	
}
 