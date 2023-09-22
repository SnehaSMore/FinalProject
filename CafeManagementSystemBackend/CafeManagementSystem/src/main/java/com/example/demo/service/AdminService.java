package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Admin;

public interface AdminService 
{
	public List<Admin> findAll();
	public void updateAdmin(Admin admin);
	Admin findByUsername(String username);
	public void deleteById(int id);
	public Optional<Admin>findById(int id);
	public void addAdmin(Admin admin);
}
