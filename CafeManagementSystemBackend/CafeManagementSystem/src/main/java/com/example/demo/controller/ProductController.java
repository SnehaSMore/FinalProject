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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ProductRepository;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/product")
public class ProductController 
{
	@Autowired
	ProductService service;
	@GetMapping("/productlist")
	public ResponseEntity<List<Product>>findAll()
	{
		return new 	ResponseEntity<List<Product>>(this.service.findAll(), HttpStatus.OK);	
	}
	@Autowired
	ProductRepository dao;
	@PostMapping("/addproduct")
	public ResponseEntity<Map<String,String>>saveproduct(@RequestBody Product p)
	{
		try
		{
			Optional<Product>existingproduct=this.dao.findById(p.getProductId());
			if(existingproduct.isEmpty())
			{
				this.service.save(p);
				Map<String,String>response=new HashMap<String,String>();
				response.put("status", "success");
	            response.put("message", "Product data added!!");
	            return new ResponseEntity<Map<String,String>>(response, HttpStatus.CREATED);
	         }
			else
			{
                Map<String,String> response=new HashMap<String,String>();
                response.put("status", "failed");
                response.put("message", "Product already  found!!");
                return new ResponseEntity<Map<String,String>>(response, HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception e1)
		{
			Map<String,String> response=new HashMap<String,String>();
            response.put("status", "failed");
            response.put("message", "Product not updated!!");
            return new ResponseEntity<Map<String,String>>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<?>getProductById(@PathVariable int id)
	{
		if(this.service.findById(id).isPresent())
		{
			return new ResponseEntity<Product>(this.service.findById(id).get(),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Product Id not found!", HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/update")
	public ResponseEntity<Map<String,String>> updateproduct(@RequestBody Product p)
	{
		try
		{
			if(this.dao.findById(p.getProductId()).isPresent())
			{
				Product existingProduct=this.dao.findById(p.getProductId()).get();
				existingProduct.setProductName(p.getProductName());
				
				this.service.updateproduct(p);
				Map<String,String> response=new HashMap<String,String>();
	            response.put("status", "success");
	            response.put("message", "Product data updated!!");
	            return new ResponseEntity<Map<String,String>>(response, HttpStatus.CREATED);
	            }
	            else
	            {
	                Map<String,String> response=new HashMap<String,String>();
	                response.put("status", "failed");
	                response.put("message", "Product data  not found!!");
	                return new ResponseEntity<Map<String,String>>(response, HttpStatus.NOT_FOUND);
	            }
			}
			 catch(Exception e1)
		        {
		            Map<String,String> response=new HashMap<String,String>();
		            response.put("status", "failed");
		            response.put("message", "Product not updated!!");
		            return new ResponseEntity<Map<String,String>>(response, HttpStatus.BAD_REQUEST);
		        }
		}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String,String>> deleteById(@PathVariable int id)
	{
		try
		{
			this.service.deleteById(id);
			Map<String,String> response=new HashMap<String,String>();
			response.put("status", "success");
			response.put("message", "Product data deleted!!");
			return new ResponseEntity<Map<String,String>>(response, HttpStatus.OK);
		}
		catch(Exception e)
		{
			Map<String,String> response=new HashMap<String,String>();
			response.put("status", "failed");
			response.put("message", "Product data not deleted!!");
			return new ResponseEntity<Map<String,String>>(response, HttpStatus.NOT_FOUND);
		}
	}

}

