package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.EmailService;
@RestController
public class EmailController {
	@Autowired
	private EmailService emailService;
	@RequestMapping("/email")
	public ResponseEntity<String> checkEmail()
	{
	emailService.sendEmail("moresneha83@gmail.com", "Check", "Checking Email");
	return new ResponseEntity<>("Message Send",HttpStatus.CREATED);
	}
}
