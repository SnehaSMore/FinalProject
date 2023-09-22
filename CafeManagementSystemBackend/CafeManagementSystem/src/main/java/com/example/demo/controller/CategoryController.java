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

import com.example.demo.dao.CategoryRepository;
import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/categ")
public class CategoryController
{
    @Autowired
    CategoryService service;
    @GetMapping("/view-category")
    public ResponseEntity<List<Category>>findAll()
    {
        return new ResponseEntity<List<Category>>(this.service.findAll(), HttpStatus.OK);
    }
    @Autowired
	CategoryRepository dao;
    @PostMapping("/addcateg")
    public ResponseEntity<Map<String,String>> savecategory(@RequestBody Category c)
    {
        try
        {
            Optional<Category> existingcategory=this.dao.findById(c.getCategoryId());
            if(existingcategory.isEmpty())
            {
            this.service.save(c);
            Map<String,String> response=new HashMap<String,String>();
            response.put("status", "success");
            response.put("message", "Category data added!!");
            return new ResponseEntity<Map<String,String>>(response, HttpStatus.CREATED);
            }
            else
            {
                Map<String,String> response=new HashMap<String,String>();
                response.put("status", "failed");
                response.put("message", "Category already  found!!");
                return new ResponseEntity<Map<String,String>>(response, HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception e1)
        {
            Map<String,String> response=new HashMap<String,String>();
            response.put("status", "failed");
            response.put("message", "Category not updated!!");
            return new ResponseEntity<Map<String,String>>(response, HttpStatus.BAD_REQUEST);
        }
        
        
    }
    @GetMapping("/find/{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable int id)
	{
		if(this.service.findById(id).isPresent())
		{
			return new ResponseEntity<Category>(this.service.findById(id).get(),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Category Id  not found!",HttpStatus.NOT_FOUND);
		}
	}
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String,String>> updatecategory(@RequestBody Category c)
    {
        try
        {
            if(this.dao.findById(c.getCategoryId()).isPresent())
            {
            	Category existingCategory=this.dao.findById(c.getCategoryId()).get();
            	existingCategory.setCategoryName(c.getCategoryName());
          
            this.service.updatecategory(c);
            Map<String,String> response=new HashMap<String,String>();
            response.put("status", "success");
            response.put("message", "Category data updated!!");
            return new ResponseEntity<Map<String,String>>(response, HttpStatus.CREATED);
            }
            else
            {
                Map<String,String> response=new HashMap<String,String>();
                response.put("status", "failed");
                response.put("message", "Category data  not found!!");
                return new ResponseEntity<Map<String,String>>(response, HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception e1)
        {
            Map<String,String> response=new HashMap<String,String>();
            response.put("status", "failed");
            response.put("message", "Category not updated!!");
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
			response.put("message", "Category data deleted!!");
			return new ResponseEntity<Map<String,String>>(response, HttpStatus.OK);
		}
		catch(Exception e)
		{
			Map<String,String> response=new HashMap<String,String>();
			response.put("status", "failed");
			response.put("message", "Category data not deleted!!");
			return new ResponseEntity<Map<String,String>>(response, HttpStatus.NOT_FOUND);
		}
	}
    
    @GetMapping("/search")
    public ResponseEntity<List<Category>> getCategoryByname(@RequestParam("categoryName") String categoryName)
    {
    	//postTitle=postTitle.toLowerCase();
    	return new ResponseEntity<List<Category>>(this.service.findByCategoryname(categoryName), HttpStatus.OK);
    }
    	@GetMapping("/categ/search")
    	public ResponseEntity<?> getCategoryName(@RequestParam("categoryName") String categoryName)
    	{
    		//postTitle=postTitle.toLowerCase();
    		if(this.service.getCategoryByname(categoryName).isPresent())
    		{
    			return new ResponseEntity<Category>(this.service.getCategoryByname(categoryName).get(),HttpStatus.OK);
    		}
    		else
    		{
    			return new ResponseEntity<String>("No Category found!",HttpStatus.NOT_FOUND);
    		}
    	}
   


}



