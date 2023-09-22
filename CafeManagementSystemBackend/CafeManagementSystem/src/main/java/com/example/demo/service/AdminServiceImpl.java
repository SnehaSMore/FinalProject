package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AdminRepository;
import com.example.demo.entity.Admin;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository dao;
	@Override
	public List<Admin> findAll() 
	{
		// TODO Auto-generated method stub
	    return dao.findAll();
	}

	@Override
	public void updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		this.dao.save(admin);
	}

	@Override
	public Admin findByUsername(String username) {
		// TODO Auto-generated method stub
		return dao.findAdminByUsername(username);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);	
	}

	@Override
	public Optional<Admin> findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public void addAdmin(Admin admin) {
		// TODO Auto-generated method stub
		this.dao.save(admin);
	}

}
