package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="FeedBack")
public class FeedBack {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="feedBackId")
	private int feedBackId;
	
	@NotNull(message = "Customer name can not be empty")
	@Size(max = 20,message = "Customer name can't be more than 20 characters")
	@Size(min=2, message="Customer name must be more than 2 characters")
	@Column(name="customerName")
	private String customerName;
	
	@NotNull(message = "Customer FeedBack can not be empty")
	@Size(min=2, message="Customer FeedBack must be more than 2 characters")
	@Column(name="custFeedBack")
	private String custFeedBack;
	
	public FeedBack()
	{
		
	}

	public FeedBack(int feedBackId, String customerName,String custFeedBack) {
		super();
		this.feedBackId = feedBackId;
		this.customerName=customerName;
		this.custFeedBack = custFeedBack;
	}

	public int getFeedBackId() {
		return feedBackId;
	}

	public void setFeedBackId(int feedBackId) {
		this.feedBackId = feedBackId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustFeedBack() {
		return custFeedBack;
	}

	public void setCustFeedBack(String custFeedBack) {
		this.custFeedBack = custFeedBack;
	}
	
	
}
