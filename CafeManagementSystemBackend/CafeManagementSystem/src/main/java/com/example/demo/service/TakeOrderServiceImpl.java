package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TakeOrderRepository;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Product;
import com.example.demo.entity.TakeOrder;

@Service
public class TakeOrderServiceImpl implements TakeOrderService {


	private final TakeOrderRepository takeOrderRepository;

	@Autowired
	public TakeOrderServiceImpl(TakeOrderRepository takeOrderRepository) {
		this.takeOrderRepository = takeOrderRepository;
	}
	
	@Autowired
	CustomerService customerservice;
	@Autowired
	ProductService productservice;
	
	@Override
	public List<TakeOrder> getAllTakeOrders() {
		// TODO Auto-generated method stub
		return takeOrderRepository.findAll();
	}

	@Override
	public TakeOrder getOrderById(long orderId) {
		// TODO Auto-generated method stub
		return takeOrderRepository.findById(orderId).orElse(null);
	}

	@Override
	public TakeOrder saveOrder(TakeOrder order, int productId) {
		// TODO Auto-generated method stub
		// Check if customer exists
//		Customer customer = customerservice.findUserById(customerId).orElse(null);
//		if (customer == null) 
//		{
//		 throw new IllegalArgumentException("Invalid customer ID");
//		}
		
		Product product = productservice.findById(productId).orElse(null);
		if (product == null) 
		{
		 throw new IllegalArgumentException("Invalid Product product ID");
		}
		order.setOrderDate(new Date()); // Set the current date as the order date
		order.setOrderTime(new Date()); // Set the current time as the order time
		// Calculate total price
		order.calculateTotalPrice();
		// Set initial order status
		order.setStatus("pending");
		
//		order.setCustomer(customer);
		order.setProduct(product);
		
		// Set total price in the entity to be saved in the database
		order.setTotalPrice(order.getTotalPrice());

		// Save the order
		return takeOrderRepository.save(order);
	}

	@Override
	public void deleteOrder(long orderId) {
		// TODO Auto-generated method stub
		takeOrderRepository.deleteById(orderId);
	}

	@Override
	public List<TakeOrder> findTakeOrdersByCustomerId(long customerId) {
		// TODO Auto-generated method stub
		return takeOrderRepository.findByCustomerCustomerId(customerId);
	}
	
	public List<TakeOrder> findTakeOrdersByProductId(long productId) {
		// TODO Auto-generated method stub
		return takeOrderRepository.findByCustomerCustomerId(productId);
	}
}