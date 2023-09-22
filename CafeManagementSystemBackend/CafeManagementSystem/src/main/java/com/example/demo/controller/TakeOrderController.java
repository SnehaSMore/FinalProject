package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.TakeOrder;
import com.example.demo.exception.TakeOrderNotFoundException;
import com.example.demo.service.TakeOrderService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/orders")
public class TakeOrderController {
  
	private final TakeOrderService takeOrderService;
	
	@Autowired
	 public TakeOrderController(TakeOrderService takeOrderService)
	 {
		this.takeOrderService=takeOrderService;
	 }
	
	@GetMapping("/vieworders")
	public ResponseEntity<List<TakeOrder>>getAllTakeOrders() 
	{
		List<TakeOrder> takeorders = takeOrderService.getAllTakeOrders();
		return new ResponseEntity<>(takeorders, HttpStatus.OK);
	}
	@GetMapping("/{orderId}")
	public ResponseEntity<TakeOrder>getOrderById(@PathVariable Long orderId)
	{
		TakeOrder takeOrder=takeOrderService.getOrderById(orderId);
		if (takeOrder == null)
		{
			throw new TakeOrderNotFoundException("Order with ID " + orderId + " not found.");
		}
		return new ResponseEntity<>(takeOrder,HttpStatus.OK);	
	}
	
	@PostMapping("/create/{productId}")
	public ResponseEntity<TakeOrder> createTakeOrder(@RequestBody TakeOrder takeOrder,@PathVariable int productId)
	{
		TakeOrder savedTakeOrder = takeOrderService.saveOrder(takeOrder,  productId);
		return ResponseEntity.ok(savedTakeOrder);		
	}
	@DeleteMapping("/delete/{orderId}")
	public ResponseEntity<Void> deleteOrder(@PathVariable long orderId)
	{
		takeOrderService.deleteOrder(orderId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<?>getTakeOrdersByCustomerId(@PathVariable long customerId)
	{
		try
		{
			List<TakeOrder> takeOrders = takeOrderService.findTakeOrdersByCustomerId(customerId);
			if(takeOrders.isEmpty())
			{
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();//no order founds
			}
			return ResponseEntity.ok(takeOrders);
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred: " + e.getMessage());
		}
	}
	
}

