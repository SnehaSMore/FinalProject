package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "admin")
public class Admin 
{
	 @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     @Column(name = "adminId")
     private int adminId;
	
	 @NotBlank(message = "Admin name cannot be blank")
	 @Size(min = 2, max = 30, message = "Admin name must be between 2 and 30 characters")
     @Column(name = "adminName")
     private String adminName;
	 
	 @NotBlank(message = "Admin phone cannot be blank")
     @Size(min = 10, max = 11, message = "Admin phone must be between 10 and 11 digits")
     @Column(name = "adminPhoneNo")
	 private String adminPhoneNo;
	 
	 @NotBlank(message = "Username cannot be blank")
	 @Size(min = 2, max = 20, message = "Username must be between 2 and 20 characters")
     @Column(name = "username")
     private String username;
	 
	 @NotBlank(message = "Admin password cannot be blank")
	 @Size(min = 4, message = "Admin password must be at least 4 characters")
	 @Column(name = "adminpassword")
	 private String adminpassword;

	 public Admin()
	 {
		 
	 }
	 
   	 public Admin(int adminId, String adminName, String adminPhoneNo, String username, String adminpassword) 
   	 {
	   super();
	   this.adminId = adminId;
	   this.adminName = adminName;
	   this.adminPhoneNo = adminPhoneNo;
	   this.username = username;
	   this.adminpassword = adminpassword;		
   	 }
 	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminPhoneNo() {
		return adminPhoneNo;
	}
	public void setAdminPhoneNo(String adminPhoneNo) {
		this.adminPhoneNo = adminPhoneNo;
	}
	public String getusername() {
		return username;
	}
	public void setusername(String username) {
		this.username = username;
	}

	public String getAdminpassword() {
		return adminpassword;
	}

	public void setAdminpassword(String adminpassword) {
		this.adminpassword = adminpassword;
	}
	
}
