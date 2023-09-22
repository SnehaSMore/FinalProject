package com.example.demo.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Product")
public class Product 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="productId")
	private int productId;
	
	@NotNull(message = "Product name can not be empty")
	@Size(max = 400,message = "Product name can't be more than 400 characters")
	@Size(min=2, message="Product name must be more than 2 characters")
	@Column(name="product_name")
	private String productName;
	
	@NotNull(message = "Product description can not be empty")
	@Size(max = 500,message = "Product description can't be more than 500 characters")
	@Size(min=1, message="Product description must be more than 2 characters")
	@Column(name="description")
	private String description;
	
	@NotNull(message = "Product price can not be empty")
	@Column(name="productPrice")
	private float productPrice;
	
	@NotNull(message = "Product price can not be empty")
	@Column(name="productCategory")
	private String category;
	
	
	public Product()
	{
		
	}

	public Product(int productId, String productName, String description,float productPrice, String category) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.productPrice =productPrice;
		this.category = category;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
