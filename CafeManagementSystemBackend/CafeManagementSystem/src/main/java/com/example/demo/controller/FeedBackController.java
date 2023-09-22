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

import com.example.demo.dao.FeedBackRepository;
import com.example.demo.entity.FeedBack;
import com.example.demo.service.FeedBackService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/feedback")
public class FeedBackController 
{
    @Autowired
    FeedBackService service;
    @GetMapping("/feedbacklist")
    public ResponseEntity<List<FeedBack>>findAll()
    {
		return new ResponseEntity<List<FeedBack>>(this.service.findAll(),HttpStatus.OK);	
    }
    
    @Autowired
    FeedBackRepository dao;
    @PostMapping("/addfeedback")
    public ResponseEntity<Map<String,String>>savefeedback(@RequestBody FeedBack fb)
    {
		try
		{
			Optional<FeedBack>existingfeedback=this.dao.findById(fb.getFeedBackId());
			if(existingfeedback.isEmpty())
			{
				this.service.save(fb);
				Map<String,String>response=new HashMap<String,String>();
				response.put("status", "success");
	            response.put("message", "FeedBack data added!!");
	            return new ResponseEntity<Map<String,String>>(response, HttpStatus.CREATED);	
			}
			else
			{
				Map<String,String> response=new HashMap<String,String>();
                response.put("status", "failed");
                response.put("message", "FeedBack already  found!!");
                return new ResponseEntity<Map<String,String>>(response, HttpStatus.NOT_FOUND);	
			}
		}
		catch(Exception e1)
		{
			Map<String,String> response=new HashMap<String,String>();
            response.put("status", "failed");
            response.put("message", "FeedBack not updated!!");
            return new ResponseEntity<Map<String,String>>(response, HttpStatus.BAD_REQUEST);
		}
    	
    }
    
    @GetMapping("/find/{id}")
    public ResponseEntity<?>getFeedBackById(@PathVariable int id)
    {
    	if(this.service.findById(id).isPresent())
    	{
    		return new ResponseEntity<FeedBack>(this.service.findById(id).get(),HttpStatus.OK);    		
    	}
    	else
		{
			return new ResponseEntity<String>("FeedBack Id not found!", HttpStatus.NOT_FOUND);
		}
    }
    
    @PutMapping("/update")
    public ResponseEntity<Map<String,String>> updatefeedback(@RequestBody FeedBack fb)
    {
    	try
		{
			if(this.dao.findById(fb.getFeedBackId()).isPresent())
			{
				FeedBack existingfeedback=this.dao.findById(fb.getFeedBackId()).get();
				existingfeedback.setCustomerName(fb.getCustomerName());
				
				this.service.updatefeedback(fb);
				Map<String,String> response=new HashMap<String,String>();
	            response.put("status", "success");
	            response.put("message", "Feedback data updated!!");
	            return new ResponseEntity<Map<String,String>>(response, HttpStatus.CREATED);
			}
			else
			{
				Map<String,String> response=new HashMap<String,String>();
                response.put("status", "failed");
                response.put("message", "Feedback data  not found!!");
                return new ResponseEntity<Map<String,String>>(response, HttpStatus.NOT_FOUND);
			}
		 }
    	catch(Exception e1)
    	{
    		Map<String,String> response=new HashMap<String,String>();
            response.put("status", "failed");
            response.put("message", "Feedback not updated!!");
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
			response.put("message", "Feedback data deleted!!");
			return new ResponseEntity<Map<String,String>>(response, HttpStatus.OK);
		}
		catch(Exception e)
		{
			Map<String,String> response=new HashMap<String,String>();
			response.put("status", "failed");
			response.put("message", "Feedback data not deleted!!");
			return new ResponseEntity<Map<String,String>>(response, HttpStatus.NOT_FOUND);
		}
	}

}