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

import com.example.demo.dao.CustomerRepository;
import com.example.demo.entity.Customer;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.service.CustomerService;
import com.example.demo.service.EmailService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	CustomerService service;
	@Autowired
	CustomerRepository dao;
	@Autowired
	EmailService emailservice;
	@PostMapping("/signup")
	public ResponseEntity<Map<String, String>> singup(@RequestBody Customer customer) {
		this.service.addCustomer(customer);
		Map<String, String> response = new HashMap<String, String>();
		response.put("status", "success");
		response.put("message", "User registered!!");
		emailservice.sendEmail(customer.getEmail(), "SignUp Email", "SignUP is Successfull in Cafe Managment System as a Customer. \nYour username is :"+customer.getUsername());
		return new ResponseEntity<Map<String, String>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/viewcustomer")
	public ResponseEntity<List<Customer>> findAll() {
		return new ResponseEntity<List<Customer>>(this.service.findALL(), HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable long id) {
		Optional<Customer> customerOptional = dao.findById(id);

		if (customerOptional.isPresent()) {
			return ResponseEntity.ok(customerOptional.get());
		} else {
			throw new CustomerNotFoundException("Customer with Id " + id + " not found.");
		}
	}

	@GetMapping("/username/{username}")
	public ResponseEntity<Customer> getCustomerByUsername(@PathVariable String username) {
		Customer customer = service.getCustomerByUsername(username);
		if (customer == null) {
			throw new CustomerNotFoundException("Customer with username " + username + " not found.");
		}
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Map<String, String>> updateUser(@RequestBody Customer e) {
		try {
			if (this.dao.findById(e.getCustomerId()).isPresent()) {
				Customer existingCus = this.dao.findById(e.getCustomerId()).get();
				existingCus.setCustomerName(e.getCustomerName());
				existingCus.setCustomerPhone(e.getCustomerPhone());
				existingCus.setUsername(e.getUsername());
				existingCus.setUserpassword(e.getUserpassword());
				existingCus.setEmail(e.getEmail());
				this.dao.save(existingCus);

				Map<String, String> response = new HashMap<String, String>();
				response.put("status", "success");
				response.put("message", "User data updated!!");
				return new ResponseEntity<Map<String, String>>(response, HttpStatus.CREATED);
			} else {
				Map<String, String> response = new HashMap<String, String>();
				response.put("status", "failed");
				response.put("message", "User data not found!!");
				return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e1) {
			Map<String, String> response = new HashMap<String, String>();
			response.put("status", "failed");
			response.put("message", "User data not updated!!");
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, String>> deleteUser(@PathVariable(name = "id") int id) {
		try {
			this.service.deleteById(id);
			Map<String, String> response = new HashMap<String, String>();
			response.put("status", "success");
			response.put("message", "user  data deleted!!");
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, String> response = new HashMap<String, String>();
			response.put("status", "failed");
			response.put("message", "Employee data not deleted!!");
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/loginn")
	public ResponseEntity<Map<String, String>> login(@RequestParam("username") String username,
			@RequestParam("userpassword") String password) {
		Optional<Customer> existingCustomer = this.service.getUserByName(username);
		Map<String, String> response = new HashMap<String, String>();
		if (existingCustomer.isPresent()) {
			if (existingCustomer.get().getUserpassword().equals(password)) {
				response.put("status", "success");
				response.put("message", "User authenticated");
				response.put("CustomerId", String.valueOf(existingCustomer.get().getCustomerId()));
				response.put("CustomerName", existingCustomer.get().getCustomerName());
				return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
			} else {
				response.put("status", "Failed");
				response.put("message", "User password incorrect");
				return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
			}
		} else {
			response.put("status", "Failed");
			response.put("message", "User does not exist");
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody Customer customerData) {
		Customer customer = service.findByUsername(customerData.getUsername());
		if (customer.getUserpassword().equals(customerData.getUserpassword())) {
			Customer sendcustomer = new Customer();
			sendcustomer.setCustomerId(customer.getCustomerId());
			sendcustomer.setCustomerName(customer.getCustomerName());
			sendcustomer.setCustomerPhone(customer.getCustomerPhone());
			sendcustomer.setUsername(customer.getUsername());
			sendcustomer.setEmail(customer.getEmail());

			return ResponseEntity.ok(sendcustomer);
		} else {
			return (ResponseEntity<?>) ResponseEntity.internalServerError();
		}
	}
}
