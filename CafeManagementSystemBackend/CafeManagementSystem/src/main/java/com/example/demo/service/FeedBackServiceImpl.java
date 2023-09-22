package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.FeedBackRepository;
import com.example.demo.entity.FeedBack;

@Service
public class FeedBackServiceImpl implements FeedBackService {

	@Autowired
	FeedBackRepository dao;
	@Override
	public List<FeedBack> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public void save(FeedBack fb) {
		// TODO Auto-generated method stub
		dao.save(fb);
	}

	@Override
	public Optional<FeedBack> findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public void updatefeedback(FeedBack fb) {
		// TODO Auto-generated method stub
		dao.save(fb);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	@Override
	public List<FeedBack> findBycustomerName(String customerName) {
		// TODO Auto-generated method stub
		return this.dao.findBycustomerNameIgnoreCase(customerName);
	}

	@Override
	public Optional<FeedBack> getcustomerByName(String customerName) {
		// TODO Auto-generated method stub
		return this.dao.findcustomerByName(customerName);
	}

}
