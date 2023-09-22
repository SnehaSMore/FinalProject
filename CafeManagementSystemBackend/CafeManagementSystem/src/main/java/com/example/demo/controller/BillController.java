package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.BillRepository;
import com.example.demo.entity.Bill;
import com.example.demo.entity.Category;
import com.example.demo.exception.BillIDNotFoundException;
import com.example.demo.exception.BillNotFoundException;
import com.example.demo.service.BillService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/bill")
public class BillController 
{
		@Autowired
		private BillService billService;
		
		@PostMapping("{orderId}/{customerId}")
		public ResponseEntity<Bill>addBill(@RequestBody Bill bill,@PathVariable long orderId,@PathVariable long customerId)
		{
			return new ResponseEntity<Bill>(billService.addBill(bill, orderId, customerId),HttpStatus.CREATED);		
		}
		
		@GetMapping("/viewBills")
		public List<Bill>getAllBills()
		{
			return billService.getAllBills();
		}
		
		@DeleteMapping("/delete/{billId}")
		public ResponseEntity<Map<String,String>> deleteBill(@PathVariable long billId)
		{
			billService.deleteBill(billId);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		@GetMapping("/customer/{customerId}")
		public ResponseEntity<List<Bill>> getBillsByCustomerId(@PathVariable long customerId)
		{
			List<Bill> bills= billService.findBillsByCustomerId(customerId);
			if(bills.isEmpty())
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(bills,HttpStatus.OK);
		}
		
		@GetMapping("/{billId}")
		public ResponseEntity<Bill>findBillById(@PathVariable long billId)
		{
		Optional<Bill> bill= billService.findBillById(billId);
		if(!bill.isPresent())
		{
			throw new BillNotFoundException("Bill with Id " + billId +"not found" );
		}
		return new ResponseEntity<>(bill.get(),HttpStatus.OK);
		
		}
		 @GetMapping("/orderId/{orderId}")
		  public ResponseEntity<List<Bill>> getBillsByOrderId(@PathVariable long orderId)
		 {
			 List<Bill> bills=billService.getBillsByOrderId(orderId);
			 if(bills.isEmpty())
			 {
				 throw new BillNotFoundException("Bills with order ID " + orderId + " not found.");
			 }
			 return ResponseEntity.ok(bills);
		 } 
		 
		 @DeleteMapping("/deleteOrder/{orderId}")
		 public ResponseEntity<String>deleteBillsByOrderId(@PathVariable long orderId)
		 {
			 billService.deleteBillsByOrderId(orderId);
			return ResponseEntity.ok("Bills for Order ID " + orderId + " have been deleted.");
			 
		 }	 
}