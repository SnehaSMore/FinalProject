package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.TakeOrder;

public interface TakeOrderService 
{

	public List<TakeOrder>getAllTakeOrders();

	public TakeOrder getOrderById(long orderId);

	//public TakeOrder saveOrder(TakeOrder order,long customerId,int productId);

	public void deleteOrder(long orderId);
	
	public List<TakeOrder> findTakeOrdersByCustomerId(long customerId);

	public TakeOrder saveOrder(TakeOrder order, int productId);
	
	
}
