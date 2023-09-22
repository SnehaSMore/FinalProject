package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.FeedBack;

public interface FeedBackService {

	public List<FeedBack>findAll();
	public void save(FeedBack fb);
	public Optional<FeedBack>findById(int id);
	public void updatefeedback(FeedBack fb);
	public void deleteById(int id);
	public List<FeedBack>findBycustomerName(String customerName);
	public Optional<FeedBack>getcustomerByName(String customerName);
}
